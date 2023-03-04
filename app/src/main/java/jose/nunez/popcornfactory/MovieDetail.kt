package jose.nunez.popcornfactory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MovieDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        var movie_header: ImageView = findViewById(R.id.movie_header) as ImageView
        var movie_title_detail: TextView = findViewById(R.id.movie_title_detail) as TextView
        var movie_summary: TextView = findViewById(R.id.movie_summary) as TextView
        var seatsLeft: TextView = findViewById(R.id.seatsLeft) as TextView
        var buyTickets: Button = findViewById(R.id.buyTickets) as Button
        var ns = 0;
        var id = -1
        var title = ""
        val bundle = intent.extras


        if (bundle != null) {

            ns = bundle.getInt("numberSeats")
            title = bundle.getString("titulo")!!
            movie_header.setImageResource(bundle.getInt("header"))
            movie_title_detail.setText(bundle.getString("titulo"))
            movie_summary.setText(bundle.getString("sinopsis"))
            seatsLeft.setText("$ns seats Available")
            id = bundle.getInt("pos")

        }

        if (ns == 0) {
            buyTickets.isActivated = false
        } else {
            buyTickets.setOnClickListener {
                val intent: Intent = Intent(this, SeatsSelection::class.java)
                intent.putExtra("id", id)
                intent.putExtra("name", title)
                startActivity(intent)
            }
        }


    }
}