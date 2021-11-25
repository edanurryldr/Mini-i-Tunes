package com.edanuryildirim.itunes.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.PopupMenu
import com.edanuryildirim.itunes.databinding.ActivityChoosenBinding
import com.edanuryildirim.itunes.view.queryui.EBookActivity
import com.edanuryildirim.itunes.view.queryui.PodCastActivity
import com.edanuryildirim.itunes.view.queryui.ShortFilmActivity
import com.edanuryildirim.itunes.view.queryui.SoftwareActivity
import kotlinx.android.synthetic.main.activity_choosen.*

class ChoosenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChoosenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChoosenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.linear1.setOnClickListener{
            val popupMenu = PopupMenu(this,linear1)
            popupMenu.menu.add(Menu.NONE,0,0,"Podcast")

            popupMenu.setOnMenuItemClickListener{
                val id = it.itemId
                if(id==0){
                    val intent = Intent(this, PodCastActivity::class.java)
                    startActivity(intent)
                }

                false
            }
            popupMenu.show()
        }
        binding.linear2.setOnClickListener{
            val popupMenu = PopupMenu(this,linear1)
            popupMenu.menu.add(Menu.NONE,0,0,"Short Film")

            popupMenu.setOnMenuItemClickListener{
                val id = it.itemId
                if(id==0) {
                    val intent = Intent(applicationContext, ShortFilmActivity::class.java)
                    startActivity(intent)
                }

                false
            }
            popupMenu.show()
        }
        binding.linear3.setOnClickListener{
            val popupMenu = PopupMenu(this,linear1)
            popupMenu.menu.add(Menu.NONE,0,0,"E-Book")

            popupMenu.setOnMenuItemClickListener{
                val id = it.itemId
                if(id==0) {
                    val intent = Intent(applicationContext, EBookActivity::class.java)
                    startActivity(intent)
                }
                false
            }
            popupMenu.show()
        }
        binding.linear4.setOnClickListener{
            val popupMenu = PopupMenu(this,linear1)
            popupMenu.menu.add(Menu.NONE,0,0,"Software")

            popupMenu.setOnMenuItemClickListener{
                val id = it.itemId
                if(id==0) {
                    val intent = Intent(applicationContext, SoftwareActivity::class.java)
                    startActivity(intent)
                }
                false
            }
            popupMenu.show()
        }
    }
}