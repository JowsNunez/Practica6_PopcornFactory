package jose.nunez.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.forEach

class SeatsSelection : AppCompatActivity() {

    var seleccion = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seats_selection)


        val title: TextView = findViewById(R.id.titleSea)

        var posMovie = -1

        val bundle = intent.extras

        if (bundle != null) {
            title.setText(bundle.getString("name"))
            posMovie = bundle.getInt("id")
        }


        val confirm: Button = findViewById(R.id.confirm)


        confirm.setOnClickListener {

            //TODO añadir lógica para reservar el lugar seleccionado por el usuario
            // hacer una nueva actividad donde se vea el resumen de la compra

            if (seleccion > -1) {
                val seatSeleccion = findViewById(seleccion) as RadioButton
                var seatNumber: Int = Integer.parseInt(seatSeleccion.text.toString())
                var nameMovie=title.text.toString()

                var intento: Intent = Intent(this, ReservationDetails::class.java)

                intento.putExtra("seatNumber", seatNumber)
                intento.putExtra("id", posMovie)
                intento.putExtra("nameMovie", nameMovie)

                startActivity(intento)

            } else {
                Toast.makeText(this, "Please select a seat!!!", Toast.LENGTH_LONG).show()
            }

        }

        val row1: RadioGroup = findViewById(R.id.row1)
        val row2: RadioGroup = findViewById(R.id.row2)
        val row3: RadioGroup = findViewById(R.id.row3)
        val row4: RadioGroup = findViewById(R.id.row4)

//NOTE: se puede simplificar

        row1.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                row1.check(checkedId)
                seleccion = checkedId
            }

        }
        row2.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row1.clearCheck()
                row3.clearCheck()
                row4.clearCheck()
                row2.check(checkedId)
                seleccion = checkedId

            }


        }

        row3.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row1.clearCheck()
                row4.clearCheck()
                row3.check(checkedId)
                seleccion = checkedId

            }


        }

        row4.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId > -1) {
                row2.clearCheck()
                row3.clearCheck()
                row1.clearCheck()
                row4.check(checkedId)
                seleccion = checkedId

            }


        }


        // obtenemos la posicion de la pelicula en la que se seleccionara el asiento, cada group row se recorre
        // para verificar los asientos que estan ocupados y compararlo con los radiobutton, si el asiento
        // es igual al asiento del radio button entonces se cambia el estado del radio button a disabled


        //NOTE: se puede simplificar
        CatalogActivity.peliculas[posMovie].seats.forEach { e ->
            for (i in 0 until row1.childCount) {
                val radioButton = row1.getChildAt(i) as RadioButton
                var value: Int = Integer.parseInt(radioButton.text.toString())

                if (value == e.asiento) {
                    radioButton.isEnabled = false
                    continue
                }

            }
            for (i in 0 until row2.childCount) {
                val radioButton = row2.getChildAt(i) as RadioButton
                var value: Int = Integer.parseInt(radioButton.text.toString())

                if (value == e.asiento) {
                    radioButton.isEnabled = false
                    continue
                }


            }
            for (i in 0 until row3.childCount) {
                val radioButton = row3.getChildAt(i) as RadioButton
                var value: Int = Integer.parseInt(radioButton.text.toString())

                if (value == e.asiento) {
                    radioButton.isEnabled = false
                    continue

                }


            }
            for (i in 0 until row4.childCount) {
                val radioButton = row4.getChildAt(i) as RadioButton
                var value: Int = Integer.parseInt(radioButton.text.toString())

                if (value == e.asiento) {
                    radioButton.isEnabled = false
                    continue

                }


            }

        }


    }


}