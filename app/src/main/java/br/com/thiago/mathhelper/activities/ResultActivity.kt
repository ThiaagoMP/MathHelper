package br.com.thiago.mathhelper.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import br.com.thiago.mathhelper.R
import br.com.thiago.mathhelper.model.operation.result.MathResult
import com.google.gson.Gson

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultGson = this.intent.getStringExtra("result")
        val result = Gson().fromJson(resultGson, MathResult::class.java)

        val textView: TextView = findViewById(R.id.txtViewResult)

        val stringBuilder = StringBuilder()
        result.mathStep.list.forEach {
            stringBuilder.append(it).append("\n")
        }
        textView.text = stringBuilder.toString()
    }
}