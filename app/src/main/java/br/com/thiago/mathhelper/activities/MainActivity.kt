package br.com.thiago.mathhelper.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import br.com.thiago.mathhelper.R
import br.com.thiago.mathhelper.model.recipe.Recipes
import br.com.thiago.mathhelper.model.recipe.impl.BhaskaraRecipe
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.title = "Math Helper"
        onButtonClick()
    }

    private fun onButtonClick() {
        val btnBhaskara: Button = findViewById(R.id.btnBhaskara)

        btnBhaskara.setOnClickListener {
            val intent = Intent(this, OperationActivity::class.java)
            intent.putExtra("recipe", Recipes.BHASKARA.toString())
            startActivity(intent)
        }
    }

}