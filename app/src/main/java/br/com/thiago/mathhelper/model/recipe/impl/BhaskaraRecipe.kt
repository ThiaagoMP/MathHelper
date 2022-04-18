package br.com.thiago.mathhelper.model.recipe.impl

import android.util.Log
import br.com.thiago.mathhelper.model.operation.requisits.MathRequirements
import br.com.thiago.mathhelper.model.recipe.Recipe
import br.com.thiago.mathhelper.model.operation.result.MathResult
import br.com.thiago.mathhelper.model.operation.step.MathStep
import kotlin.collections.HashMap
import kotlin.math.pow
import kotlin.math.sqrt

class BhaskaraRecipe() : Recipe {

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

        val sqrtDelta = sqrt(delta)
        val step = MathStep(
            listOf(
                "Δ = $b² -4.$a.$c",
                "Δ = ${b.pow(2) - 4 * a * c}",
                "",
                "x = -$b ± √$delta",
                "-----------",
                "    2.$a    ",
                "",
                "x¹ = ${-b + sqrtDelta}",
                "-----------",
                "  ${2 * a}",
                "x¹ = ${(-b + sqrtDelta) / (2 * a)} ",
                "",
                "x² = ${-b - sqrtDelta}",
                "-----------",
                "  ${2 * a}",
                "x² = ${(-b - sqrtDelta) / (2 * a)} "
            )
        )
        return MathResult(results, step)
    }

    override fun getRequirements(): MathRequirements {
        return MathRequirements(listOf("a", "b", "c"))
    }

}