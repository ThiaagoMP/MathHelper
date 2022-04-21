package br.com.thiago.mathhelper.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import br.com.thiago.mathhelper.R
import br.com.thiago.mathhelper.model.operation.result.MathResult
import br.com.thiago.mathhelper.model.recipe.Recipes
import com.google.gson.Gson
import java.lang.Double.parseDouble


class OperationActivity : AppCompatActivity() {
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)

        val recipe = Recipes.valueOf(this.intent.getStringExtra("recipe")!!).recipe

        val linearLayout = findViewById<LinearLayout>(R.id.activity_operation)
        recipe.getRequirements().values.forEach {
            val text = EditText(this)
            text.hint = "$it                "
            text.inputType = InputType.TYPE_CLASS_PHONE
            linearLayout.addView(text)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.button_activity_operation, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != R.id.activity_form_student_menu_save) return super.onOptionsItemSelected(
            item
        )

        val recipe = Recipes.valueOf(this.intent.getStringExtra("recipe")!!).recipe

        val map: MutableMap<String, Double> = HashMap()

        val relativeLayout = findViewById<LinearLayout>(R.id.activity_operation)
        for (i in 0..relativeLayout.childCount) {
            val childAt = relativeLayout.getChildAt(i)

            if (childAt !is EditText) continue

            try {
                val value = parseDouble(childAt.text.toString())
                map[childAt.hint.toString().trim()] = value
            } catch (error: NumberFormatException) {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("Por favor preencha os campos corretamente!")
                    .setNegativeButton("Voltar") { dialogInterface, _ -> dialogInterface.cancel() }
                    .create().show()
                return super.onOptionsItemSelected(item)
            }
        }
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("result", Gson().toJson(recipe.resolve(map), MathResult::class.java))
        startActivity(intent)
        this.finish()
        return super.onOptionsItemSelected(item)
    }

}