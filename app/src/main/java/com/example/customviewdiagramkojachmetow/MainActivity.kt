package com.example.customviewdiagramkojachmetow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() { //Класс для запуска приложения(функция onCreate(), в которой фактически и создается весь интерфейс приложения) 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
