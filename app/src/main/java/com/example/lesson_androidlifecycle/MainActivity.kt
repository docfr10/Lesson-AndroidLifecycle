package com.example.lesson_androidlifecycle

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lesson_androidlifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // Типы логов
    /*
    Verbose — полного протокола системных действий
    Debug — работа с отладочной информацией
    Info — информационные сообщения
    Warn — предупреждения
    Error — ошибки
     */

    // Свойства с поздней инициализацией для элементов пользовательского интерфейса
    private lateinit var binding: ActivityMainBinding

    // Свойство для хранения информации о нажатии на кнопку
    private var buttonClick: String = ""

    // Метод onCreate — запускается самый первый при старте активности или после onStop
    // Основная цель — СОЗДАНИЕ(не показ) объектов пользовательского интерфейса
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация объекта класса Binding для общего доступа ко
        // всем объекта пользовательского интерфейса
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Вызов лога информационного сообщения
        Log.i("Метод жизненного цикла", "onCreate")

        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Присвоение webView ссылки на сайт
        binding.webView?.loadUrl("http://www.yandex.ru")

        if (savedInstanceState != null)
            buttonClick = savedInstanceState.getString("buttonClick").toString()

        if (buttonClick.isNotEmpty())
            binding.textVIew.text = buttonClick

        // Слушатель введенного текста в поле для ввода
//        editText.addTextChangedListener {
//            textView.text = it
//        }

        // Слушатель нажатий на кнопку
        binding.button.setOnClickListener {
            binding.textVIew.text = "На меня нажали однократно"
            buttonClick = "На меня нажали однократно"
        }

        // Слушатель долгих нажатий
        binding.button.setOnLongClickListener {
            binding.textVIew.text = "На меня нажали долго"
            buttonClick = "На меня нажали долго"
            return@setOnLongClickListener true
        }

        binding.switch1.setOnClickListener {
            binding.editTextText.isEnabled = binding.switch1.isChecked
        }

        binding.floatingActionButton.setOnClickListener {
            // Создание намерения перехода на другой экран
            val intent = Intent(this, MainActivity2::class.java)
            // Запуск намерения
            startActivity(intent)
        }
    }

    // Метод onStart — запускается после onCreate или onRestart
    // Подготавливает пользовательский интерфейс к показу пользователю
    override fun onStart() {
        super.onStart()
        Log.i("Метод жизненного цикла", "onStart")
    }

    // Метод onResume — запускается после onStart или onPause
    // Демонстрирует пользователю интерфейс активности(экрана)
    // После его вызова пользователь может взаимодейстовать с элементами экрана
    override fun onResume() {
        super.onResume()
        Log.i("Метод жизненного цикла", "onResume")
    }

    // Метод onPause — запускается после onResume
    // Готовит активность к: переходу в "спящий режим", перезапуску или возобновлению работы
    override fun onPause() {
        super.onPause()
        Log.i("Метод жизненного цикла", "onPause")
    }

    // Метод onStop — запускается после onPause
    // Переводит активность в "спящий режим" и готовит: возобновлению работы или уничтожению
    override fun onStop() {
        super.onStop()

        binding.textVIew.text = "OnStop"

        Log.i("Метод жизненного цикла", "onStop")
    }

    // Метод onRestart — запускается после onStop
    // Перезагружает пользовательский интерфейс, но не создает его заново
    override fun onRestart() {
        super.onRestart()
        Log.i("Метод жизненного цикла", "onRestart")
    }

    // Метод onDestroy — запускается после onStop
    // Вызывается перед уничтожением активности
    override fun onDestroy() {
        super.onDestroy()
        Log.i("Метод жизненного цикла", "onDestroy")
    }

    // Метод onSaveInstanceState вызывается для записи значений во временное Bundle хранилище
    // перед перезагрузкой активности
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Запись в Bundle хранилище значения перенной "buttonClick" с ключом "buttonClick"
        outState.putString("buttonClick", buttonClick)
    }
}












