package project.shabd.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
     var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View){
        //now if clicked on  btn i want to get its text
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View){

        //we require view here also
        tvInput?.text = " "
    }

    fun onDecimalPoint(view: View){
        if (lastNumeric && !lastDot){
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            // here we checked if the prev element was number if yes cont
            var tvValue = tvInput?.text.toString()
            var prefix = " "
            try {
                if(tvValue.startsWith("-")){
                    // here if the calc is staerting with - eg -56-67 app wont crash and will ignore
                    // first -
                    prefix = " "
                    tvValue = tvValue.substring(1)
                }

                if(tvValue.contains("-")){
                    val splitValue = tvValue.split("-")
                    // here we are splitting the operation eg 99+1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }


                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())

                } else if(tvValue.contains("+")){
                    val splitValue = tvValue.split("+")
                    // here we are splitting the operation eg 99+1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }


                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())

                } else if(tvValue.contains("*")){
                    val splitValue = tvValue.split("*")
                    // here we are splitting the operation eg 99+1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }


                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())

                }else if(tvValue.contains("/")){
                    val splitValue = tvValue.split("/")
                    // here we are splitting the operation eg 99+1
                    var one = splitValue[0] // 99
                    var two = splitValue[1] // 1
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }


                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())

                }


            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String): String{
        var value = result
        if(result.contains(".0"))
            value = result.substring(0, result.length-2)
        return value
    }
    fun onOperator(view: View){
        // for all the operator
        tvInput?.text?.let{
        if(lastNumeric && !isOperatorAdded(it.toString()))
            // we just checked if there was a numeric val and no operator before the operator we are going to add

            tvInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = false
    }
    }

    private fun isOperatorAdded(value: String): Boolean{
        return if(value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}