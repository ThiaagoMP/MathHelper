package br.com.thiago.mathhelper.model.recipe.impl

import br.com.thiago.mathhelper.model.operation.requisits.MathRequirements
import br.com.thiago.mathhelper.model.operation.result.MathResult
import br.com.thiago.mathhelper.model.operation.step.MathStep
import br.com.thiago.mathhelper.model.recipe.Recipe
import br.com.thiago.mathhelper.utils.NumberFormat
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class BhaskaraRecipe : Recipe {

    // x = – b ± √b² - 4.a.c
    // ------------- (division)
    //      2.a

    override fun resolve(values: Map<String, Double>): MathResult {
        val a: Double = values["a"] ?: 0.0
        val b: Double = values["b"] ?: 0.0
        val c: Double = values["c"] ?: 0.0

        val delta: Double = (b.pow(2)) - 4 * a * c

        val xOne: Double = ((-b) + delta) / 2 * a
        val xTwo: Double = ((-b) - delta) / 2 * a

        val results: HashMap<String, Double> = HashMap()

        results["value1"] = xOne
        results["value2"] = xTwo

        val aFormated = format(a)
        val bFormated = format(b)
        val cFormated = format(c)
        val deltaFormated = format(delta)

        val sqrtDelta = sqrt(delta)
        val sqrtDeltaFormated = format(sqrtDelta)

        val step = MathStep(
            mutableListOf(
                "Δ = $bFormated² -4 . $aFormated . $cFormated",
                "Δ = ${format(b.pow(2))} -4 . $aFormated . $cFormated",
                "Δ = $deltaFormated",
            )
        )
        if (delta < 0) {
            step.list.addAll(listOf("", "Como o Δ é negativo, logo não temos raízes reais."))
            return MathResult(results, step)
        }
        step.list.addAll(
            listOf(
                "",
                "     -($bFormated) ± √$deltaFormated",
                "x = ―――――",
                "            2.$aFormated",
                "",
                "         ${format(abs(b))} ± $sqrtDeltaFormated",
                "x = ―――――",
                "             ${format(2 * a)}",
                "",
                "             ${format(-b + sqrtDelta)}",
                "x¹ = ―――――",
                "             ${format(2 * a)}",
                "",
                "x¹ = ${format((-b + sqrtDelta) / (2 * a))} ",
                "",
                if (delta == 0.0) "Delta é igual a 0 por tanto os dois resultados são iguais!" else "",
            )
        )
        if (delta > 0) {
            step.list.addAll(
                listOf(
                    "         ${format(abs(b))} - $sqrtDeltaFormated",
                    "x² = ―――――",
                    "             ${format(2 * a)}",
                    "",
                    "             ${format(-b - sqrtDelta)}",
                    "x² = ―――――",
                    "             ${format(2 * a)}",
                    "",
                    "x² = ${format((-b - sqrtDelta) / (2 * a))} "
                )
            )
        }
        return MathResult(results, step)
    }

    private fun format(number: Double): String {
        return NumberFormat().format(number)
    }

    override fun getRequirements(): MathRequirements {
        return MathRequirements(listOf("a", "b", "c"))
    }

}