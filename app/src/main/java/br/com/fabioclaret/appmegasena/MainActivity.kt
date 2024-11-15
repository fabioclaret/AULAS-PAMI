package br.com.fabioclaret.appmegasena

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Random

class MainActivity : AppCompatActivity() {
    lateinit var editNumber:EditText
    lateinit var txtResult: TextView
    lateinit var btnAposta:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        editNumber = findViewById(R.id.numeros)
        txtResult  = findViewById(R.id.txt_resultado)
        btnAposta  = findViewById(R.id.btn_apostar)

        btnAposta.setOnClickListener {
            val text = editNumber.text.toString()

            numberGenerator(text, txtResult)
        }

    }

    private fun numberGenerator(text: String, txtResult: TextView) {
        if( text.isNotEmpty() ) {
            val qtd = text.toInt()

            if ( qtd >= 6 && qtd <=15 ){
                val numbers = mutableSetOf<Int>()
                val random = Random()

                while ( true ){
                    val number = random.nextInt(60)
                    numbers.add( number + 1 )

                    if( numbers.size == qtd ){
                        Log.e("teste", "numberGenerator: elementos do numbers ${numbers.size}", )
                        break
                    }

                    txtResult.text = numbers.toString()
                    val mostra = numbers.toString()
                    Log.e("teste", "numberGenerator: $mostra "  )
                }
            }else{
                Toast.makeText(this, "Digite um numero entre 6 e 15 para apostar", Toast.LENGTH_LONG).show()
            }

        }else{
            Toast.makeText(this, "Esse campo nao pode ficar em branco!", Toast.LENGTH_LONG).show()
        }
    }
}