package com.example.firestoreexample

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun saveNote(v: View){
        if(note_title.text.toString().isEmpty() || note_description.text.toString().isEmpty()){
            Toast.makeText(this, "Title or Description must not be empty??", Toast.LENGTH_SHORT).show()
            return
        }
        val noteTitle=note_title.text.toString()
        val noteDescription = note_description.text.toString()
        val user: MutableMap<String, Any> = HashMap()
        user[KEY_TITLE] = noteTitle
        user[KEY_DESCRIPTION] = noteDescription

        db.collection("Notebook").document("my first note")
            .set(user).addOnSuccessListener {
                Toast.makeText(this, "Note saved !! with "+it, Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error "+e, Toast.LENGTH_SHORT).show()
            }
    }

    companion object {
        private const val TAG = "Main Activity"
        private const val KEY_TITLE = "tile"
        private const val KEY_DESCRIPTION = "description"
    }
}
