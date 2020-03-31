package com.example.lab7

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class FragmentResult : Fragment() {

    private lateinit var v : View
    private lateinit var tV2:TextView
    private lateinit var btnShare: Button


    private var familyText:String =""
    private var nameText:String =""
    private var emailText:String =""
    private var score:Int = 0
    private var count:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        familyText= bundle!!.getString("family").toString()
        nameText=bundle.getString("name").toString()
        emailText=bundle.getString("email").toString()
        score=bundle.getInt("score")
        count=bundle.getInt("count")

        v = inflater.inflate(R.layout.fragment_result,container,false)
        tV2 = v.findViewById<View>(R.id.textView2) as TextView
        tV2.text = "Было задано $count вопросов, из которых Вы правльно ответили на $score"
        btnShare=v.findViewById<View>(R.id.button) as Button
        btnShare.setOnClickListener {
            share()
        }
        // Inflate the layout for this fragment
        return v
    }

    private fun share(){
        val email = Intent(Intent.ACTION_SEND)
        //Указываем получателя
        email.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailText))
        //Устанавливаем Тему сообщения
        email.putExtra(Intent.EXTRA_SUBJECT,"Результаты в игре Соответствие цветов")
        //Устанавливаем само сообщение
        email.putExtra(Intent.EXTRA_TEXT,"Было задано $count вопросов, из которых Вы правльно ответили на $score" )
        //тип отправляемого сообщения
        email.type = "message/rfc822"
        //Вызываем intent выбора клиента для отправки сообщения
        startActivity(Intent.createChooser(email, "Выберите email клиент :"))
    }
}
