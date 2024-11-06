package paba.b.latihanlistview

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import paba.b.latihanlistview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var data = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        data.addAll(listOf("1","2","3","4","5"))

        val lvAdapter = ArrayAdapter(
            this, android.R.layout.simple_list_item_1,
            data
        )

        binding.lv1.adapter = lvAdapter

        val _lv1 = findViewById<ListView>(R.id.lv1)
        _lv1.adapter = lvAdapter

        val _btnTambah = findViewById<Button>(R.id.btnTambah)
        _btnTambah.setOnClickListener {
            var dataAkhir = Integer.parseInt(data.get(data.size-1)) + 1
            data.add(dataAkhir.toString())
            lvAdapter.notifyDataSetChanged()
        }

//        buat toast ketika list di klik
        _lv1.setOnItemClickListener{ parent, view , position, id ->
            Toast.makeText(this, "${data[position]}",
                Toast.LENGTH_LONG).show()
        }

        // btn hapus
        val _btnHapus = findViewById<Button>(R.id.btnHapus)
        _btnHapus.setOnClickListener {
            data.removeFirst()
            lvAdapter.notifyDataSetChanged()
        }

        binding.sv1.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
          override fun onQueryTextSubmit(query: String?): Boolean {
              lvAdapter.getFilter().filter(query)
              return false
          }

            override fun onQueryTextChange(newText: String?): Boolean {
                lvAdapter.getFilter().filter(newText)
                return false
            }
        })
    }
}