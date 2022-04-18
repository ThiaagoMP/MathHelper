package br.com.thiago.mathhelper.model.recipe

import br.com.thiago.mathhelper.model.operation.requisits.MathRequirements
import br.com.thiago.mathhelper.model.operation.result.MathResult

interface Recipe {

    fun resolve(values: Map<String, Double>): MathResult

    fun getRequirements(): MathRequirements

}