package jose.nunez.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class ReservationDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation_details)

        val bundle = intent.extras
        var id = -1
        var seatNumber = 0
        var nameMovie = ""
        var seleccion = ""

        val debitButton: RadioButton = findViewById(R.id.debitButton) as RadioButton
        val creditButton: RadioButton = findViewById(R.id.creditButton) as RadioButton
        val buyBotton: Button = findViewById(R.id.buyButton) as Button
        val editTextname: EditText = findViewById(R.id.name) as EditText
        val txtSeatNumber: TextView = findViewById(R.id.seat_buy) as TextView
        val txtNameMovie: TextView = findViewById(R.id.title_Movie) as TextView




        if (bundle != null) {
            id = bundle.getInt("id")
            nameMovie = bundle.getString("nameMovie")!!
            seatNumber = bundle.getInt("seatNumber")

            txtNameMovie.text = "Movie: $nameMovie"

            txtSeatNumber.text = "Seat Number: $seatNumber"

        }



        debitButton.setOnCheckedChangeListener { buttonView, isChecked ->
            seleccion = "Debit Card"
            if (isChecked) {
                creditButton.isChecked = false


            }


        }

        creditButton.setOnCheckedChangeListener { buttonView, isChecked ->
            seleccion = "Credit Card"
            if (isChecked) {
                debitButton.isChecked = false

            }


        }

        buyBotton.setOnClickListener {

            var name = editTextname.text


            if (seleccion.isNotBlank() && name.isNotEmpty()) {

                CatalogActivity.peliculas[id].seats.add(Cliente(nameMovie, seleccion, seatNumber))


                startActivity(Intent(this, CatalogActivity::class.java))
                Toast.makeText(this, "Enjoy the Movie! :D $nameMovie", Toast.LENGTH_LONG).show()

            } else {
                Toast.makeText(this, "Complete all fields or select pay type ", Toast.LENGTH_LONG)
                    .show()
            }

        }


    }
}