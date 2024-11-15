package com.aplicacion.preguntasbolivia

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class GameActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var tvQuestionCounter: TextView
    private lateinit var tvTimer: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnOption1: MaterialButton
    private lateinit var btnOption2: MaterialButton
    private lateinit var btnOption3: MaterialButton
    private lateinit var btnOption4: MaterialButton

    private var currentQuestionIndex = 0
    private var score = 0
    private var timeLeft = 30 // 30 seconds per question
    private lateinit var timer: CountDownTimer
    private var currentLevel = 1 // Default level
    private lateinit var currentQuestions: List<Question>

    private val allQuestions = mapOf(
        1 to listOf(
            Question("¿Quién fue el primer presidente de Bolivia?", listOf("Simón Bolívar", "Antonio José de Sucre", "José Ballivián", "Andrés de Santa Cruz"), 1),
            Question("¿Qué conflicto tuvo lugar en 1932-1935 entre Bolivia y Paraguay?", listOf("Guerra del Chaco", "Guerra de la Triple Alianza", "Guerra Federal", "Guerra del Pacífico"), 0),
            Question("¿En qué año se fundó la ciudad de La Paz?", listOf("1548", "1538", "1550", "1540"), 0),
            Question("¿Cuál fue el objetivo principal de la Guerra del Pacífico?", listOf("Controlar los recursos de salitre", "Expansión territorial hacia la Amazonía", "Independencia de los pueblos indígenas", "Controlar el tráfico de oro y plata"), 0),
            Question("¿Qué presidente boliviano firmó el Tratado de Paz con Chile en 1904?", listOf("José Manuel Pando", "Hernando Siles", "Aniceto Arce", "Simón Bolívar"), 0),
            Question("¿Qué fecha se celebra el Día de la Independencia de Bolivia?", listOf("6 de agosto", "12 de octubre", "2 de mayo", "25 de mayo"), 0),
            Question("¿Quién fue el líder de la Revolución Nacional de 1952 en Bolivia?", listOf("Víctor Paz Estenssoro", "Hernán Siles Suazo", "Carlos Mesa", "Evo Morales"), 0),
            Question("¿Qué evento marcó el fin de la Guerra Federal en Bolivia?", listOf("La victoria de los liberales en la batalla de La Paz", "El Tratado de Paz con Chile", "La fundación de Sucre", "La Revolución Nacional de 1952"), 0),
            Question("¿Qué presidente boliviano instauró el Estado Plurinacional en 2009?", listOf("Evo Morales", "Hugo Banzer", "Gonzalo Sánchez de Lozada", "Hernán Siles Suazo"), 0),
            Question("¿Cuál fue el resultado de la Guerra Federal de 1898-1899?", listOf("La victoria de los liberales y la consolidación del poder", "La independencia de Bolivia", "La anexión de Bolivia a Chile", "La instauración de la dictadura de Banzer"), 0),
            Question("¿Qué rebelión indígena ocurrió en el siglo XVIII en Bolivia?", listOf("Rebelión de Túpac Katari", "Rebelión de Tupac Amaru", "Rebelión de los hermanos Pinochet", "Rebelión de los indígenas del Gran Chaco"), 0),
            Question("¿En qué año Bolivia se unió a la Confederación Perú-Boliviana?", listOf("1836", "1825", "1865", "1855"), 0),
            Question("¿Cuál fue el principal objetivo de la Reforma Agraria de 1953 en Bolivia?", listOf("Redistribuir tierras a los campesinos", "Eliminar el ejército", "Incrementar el número de universidades", "Independizarse de Chile"), 0),
            Question("¿Qué presidente boliviano fue derrocado por un golpe militar en 1971?", listOf("Hernán Siles Suazo", "Juan José Torres", "Víctor Paz Estenssoro", "Evo Morales"), 1),
            Question("¿Qué figura histórica fue clave en la independencia de Bolivia?", listOf("Simón Bolívar", "José de San Martín", "Manuel Belgrano", "Bernardo O'Higgins"), 0),
            Question("¿Cuál es el nombre del tratado que estableció la independencia de Bolivia de España?", listOf("Tratado de Paz y Amistad", "Tratado de La Paz", "Tratado de Sucre", "Tratado de Córdoba"), 1),
            Question("¿Qué revolución boliviana ocurrió en 1952?", listOf("La Revolución Nacional", "La Revolución de Octubre", "La Revolución Agraria", "La Revolución de la Paz"), 0),
            Question("¿Cuál fue el impacto de la Reforma Agraria de 1953 en la economía boliviana?", listOf("Reducción de las grandes propiedades y mayor acceso a la tierra para los campesinos", "Aumento de las exportaciones de minerales", "Disminución de la inversión extranjera", "Expansión del territorio boliviano"), 0),
            Question("¿Qué conflicto bélico enfrentó a Bolivia con Chile en el siglo XIX?", listOf("Guerra del Pacífico", "Guerra de la Confederación", "Guerra de la Triple Alianza", "Guerra del Chaco"), 0),
            Question("¿Quién fue el primer presidente indígena de Bolivia?", listOf("Evo Morales", "José Ballivián", "Hernán Siles Suazo", "Carlos Mesa"), 0),
            Question("¿Qué revolución llevó a la creación de la República de Bolivia?", listOf("Revolución de 1809", "Revolución de 1825", "Revolución de 1898", "Revolución de 1952"), 1),
            Question("¿Cuál fue el principal motivo de la Guerra del Chaco?", listOf("Control de las tierras ricas en petróleo y gas", "Expansión territorial hacia el norte", "Independencia de las provincias del sur", "Conflictos comerciales con Brasil"), 0),
            Question("¿Qué nombre recibía la región que fue la base de la Confederación Perú-Boliviana?", listOf("Estado de Bolivia", "República de Bolivia", "Confederación Peruano-Boliviana", "República Federal de Bolivia"), 2),
            Question("¿Qué ciudad boliviana fue la primera capital de Bolivia después de la independencia?", listOf("Sucre", "La Paz", "Potosí", "Cochabamba"), 0),
            Question("¿Cuál fue la principal causa de la crisis política en Bolivia en 2003?", listOf("La privatización del gas y petróleo", "La reforma agraria", "El conflicto con Chile", "La reforma educativa"), 0),
            Question("¿Qué golpe de estado ocurrió en Bolivia en 1964?", listOf("Golpe de Estado de Barrientos", "Golpe de Estado de Banzer", "Golpe de Estado de Siles Suazo", "Golpe de Estado de Morales"), 0),
            Question("¿Qué presidente boliviano fue conocido como 'El Tigre de los Andes'?", listOf("José Ballivián", "Simón Bolívar", "Hernán Siles Suazo", "Evo Morales"), 0),
            Question("¿Qué reforma importante ocurrió en Bolivia en 2009?", listOf("La Constitución Plurinacional", "La Reforma Agraria", "La Nacionalización de la Industria Petrolera", "La Privatización de Empresas Estatales"), 0)
        ),
        2 to listOf(
            Question("¿Cuál es el río más largo de Bolivia?", listOf("Río Mamoré", "Río Pilcomayo", "Río Beni", "Río Grande"), 3),
            Question("¿En qué departamento se encuentra el Salar de Uyuni?", listOf("Oruro", "Potosí", "La Paz", "Cochabamba"), 1),
            Question("¿Cuál es la capital de Bolivia?", listOf("Sucre", "La Paz", "Cochabamba", "Santa Cruz"), 1),
            Question("¿Qué lago boliviano es el más grande en superficie?", listOf("Lago Titicaca", "Lago Poopó", "Lago Uru Uru", "Lago Ypoa"), 0),
            Question("¿Cuál es la montaña más alta de Bolivia?", listOf("Illimani", "Illampu", "Huascarán", "Sajama"), 3),
            Question("¿En qué región de Bolivia se encuentra la ciudad de Santa Cruz?", listOf("Oriental", "Occidental", "Andina", "Chaco"), 0),
            Question("¿Cuál de los siguientes departamentos no limita con Brasil?", listOf("Potosí", "Beni", "Pando", "La Paz"), 3),
            Question("¿Qué valle es famoso por su producción de vinos en Bolivia?", listOf("Valle de la Concepción", "Valle Grande", "Valle de Cochabamba", "Valle de la Paz"), 2),
            Question("¿Qué región boliviana se encuentra al este de la Cordillera de los Andes?", listOf("El Gran Chaco", "La Llanura de Beni", "La Selva del Amazonas", "La Región de los Valles"), 1),
            Question("¿Cuál es el principal puerto de Bolivia en el océano Pacífico?", listOf("Antofagasta", "Arica", "Iquique", "No tiene puerto en el Pacífico"), 3),
            Question("¿En qué departamento se encuentra el Parque Nacional Madidi?", listOf("La Paz", "Cochabamba", "Potosí", "Beni"), 0),
            Question("¿Cuál es el nombre del desierto en el sur de Bolivia?", listOf("Desierto de Atacama", "Desierto de Salinas", "Desierto del Gran Chaco", "Desierto de Uyuni"), 3),
            Question("¿Qué ciudad boliviana es conocida por su arquitectura colonial y su casco antiguo?", listOf("Sucre", "La Paz", "Santa Cruz", "Cochabamba"), 0),
            Question("¿En qué región se encuentra la ciudad de Potosí?", listOf("Altiplano", "Valles", "Llanos", "Selva"), 0),
            Question("¿Cuál es el nombre del río que atraviesa la ciudad de La Paz?", listOf("Río Beni", "Río Pilcomayo", "Río Choqueyapu", "Río Mamoré"), 2),
            Question("¿Qué región del país es conocida por su clima tropical y su biodiversidad?", listOf("El Oriente", "El Altiplano", "Los Valles", "La Llanura del Gran Chaco"), 0),
            Question("¿Qué ciudad boliviana está situada a orillas del Lago Titicaca?", listOf("Cochabamba", "Sucre", "La Paz", "Santa Cruz"), 2),
            Question("¿Qué parque nacional es famoso por sus formaciones rocosas de colores?", listOf("Parque Nacional Sajama", "Parque Nacional Tunari", "Parque Nacional Eduardo Avaroa", "Parque Nacional Madidi"), 2),
            Question("¿Cuál es la capital del departamento de Cochabamba?", listOf("Santa Cruz", "Sucre", "Cochabamba", "Oruro"), 2),
            Question("¿Qué valle de Bolivia es conocido por su agricultura?", listOf("Valle de La Paz", "Valle de Cochabamba", "Valle de Santa Cruz", "Valle de Sucre"), 1),
            Question("¿En qué departamento se encuentra la ciudad de Tarija?", listOf("Potosí", "Santa Cruz", "Tarija", "Cochabamba"), 2),
            Question("¿Cuál es el nombre del área natural protegida que se encuentra en el departamento de Oruro?", listOf("Parque Nacional Sajama", "Reserva Nacional de Fauna Andina Eduardo Avaroa", "Reserva Biológica de Pilón Lajas", "Parque Nacional Madidi"), 0),
            Question("¿Qué río es uno de los principales afluentes del río Amazonas en Bolivia?", listOf("Río Pilcomayo", "Río Mamoré", "Río Paraguay", "Río Beni"), 1),
            Question("¿Cuál es el nombre del cerro que se encuentra al sur de la ciudad de La Paz?", listOf("Cerro Chacaltaya", "Cerro Huayna Potosí", "Cerro Illimani", "Cerro Sajama"), 0),
            Question("¿Qué región boliviana se caracteriza por sus valles interandinos?", listOf("El Altiplano", "Los Llanos", "Los Valles", "La Selva"), 2),
            Question("¿Cuál es el nombre del mayor sistema fluvial de Bolivia en la región de los Llanos?", listOf("Río Beni", "Río Pilcomayo", "Río Paraguay", "Río Piraí"), 0),
            Question("¿Qué departamento de Bolivia es conocido por su producción de gas natural y petróleo?", listOf("Santa Cruz", "Cochabamba", "Potosí", "Tarija"), 0),
            Question("¿Cuál es el nombre del lago salado más grande del mundo que se encuentra en Bolivia?", listOf("Lago Poopó", "Lago Titicaca", "Salar de Uyuni", "Lago Uru Uru"), 2),
            Question("¿Qué región de Bolivia es conocida por su producción de quinoa?", listOf("Los Valles", "El Altiplano", "La Llanura de Beni", "El Oriente"), 1),
            Question("¿Cuál es la ciudad más grande de Bolivia en términos de población?", listOf("La Paz", "Santa Cruz", "Cochabamba", "Sucre"), 1)
        ),
        3 to listOf(
            Question("¿Cuál es el festival más importante de Bolivia celebrado en febrero?", listOf("Carnaval de Oruro", "Fiesta de la Virgen de Urkupiña", "Fiesta de la Alasita", "Festival de la Virgen de Copacabana"), 0),
            Question("¿Qué baile tradicional es originario de la región andina de Bolivia?", listOf("Morenada", "Samba", "Cueca", "Tango"), 0),
            Question("¿Qué plato típico boliviano se prepara con carne de llama y maíz?", listOf("Salteña", "Pique macho", "Sajta de pollo", "Chairo"), 3),
            Question("¿Qué bebida alcohólica tradicional se elabora a base de maíz fermentado en Bolivia?", listOf("Singani", "Chicha", "Cerveza", "Aguardiente"), 1),
            Question("¿Cuál es el nombre del mercado tradicional en La Paz donde se venden productos artesanales y comida?", listOf("Mercado de las Brujas", "Mercado Central", "Mercado Camacho", "Mercado Rodríguez"), 0),
            Question("¿Qué celebración se realiza en Bolivia para rendir homenaje a la Pachamama?", listOf("Fiesta de la Pachamama", "Día de Todos los Santos", "Festival del Inti Raymi", "Carnaval de Oruro"), 0),
            Question("¿Qué figura religiosa es venerada en la fiesta de la Virgen de Copacabana?", listOf("Virgen de Copacabana", "Virgen de Urkupiña", "Virgen de la Candelaria", "Virgen de la Asunción"), 0),
            Question("¿Cuál es la danza tradicional que representa la lucha entre el bien y el mal en el Carnaval de Oruro?", listOf("Diablada", "Morenada", "Caporales", "Wititi"), 0),
            Question("¿Qué tradición se lleva a cabo el 6 de agosto para celebrar la independencia de Bolivia?", listOf("Desfile militar", "Fiesta en la Plaza Murillo", "Ceremonia religiosa", "Competencia de danzas"), 0),
            Question("¿Qué tipo de música es característica de la región del Altiplano boliviano?", listOf("Música andina", "Salsa", "Cumbia", "Rock"), 0),
            Question("¿Cuál es el nombre del pan tradicional boliviano que se come en la fiesta de Todos los Santos?", listOf("Tanta Wawa", "Salteña", "Empanada", "Pan de Pascua"), 0),
            Question("¿Qué lugar en Bolivia es conocido por su arquitectura colonial y su historia como antigua capital?", listOf("Sucre", "La Paz", "Cochabamba", "Santa Cruz"), 0),
            Question("¿Qué fiesta tradicional boliviana celebra la cosecha de papa en la región andina?", listOf("Fiesta de la Papa", "Fiesta de la Pachamama", "Fiesta del Inti Raymi", "Fiesta de la Alasita"), 0),
            Question("¿Qué figura mitológica boliviana es conocida como el espíritu de los andes?", listOf("Pachamama", "El Tío", "El Chaneque", "El Súcubo"), 1),
            Question("¿Qué instrumento musical andino es típico de Bolivia?", listOf("Siku", "Guitarra", "Piano", "Saxofón"), 0),
            Question("¿Qué festividad se celebra el 2 de noviembre para recordar a los difuntos en Bolivia?", listOf("Día de los Santos", "Día de la Independencia", "Fiesta de Todos los Santos", "Fiesta de la Virgen de Urkupiña"), 2),
            Question("¿Qué festival se celebra en el mes de enero en la ciudad de La Paz, conocido por sus danzas y comida tradicional?", listOf("Fiesta de la Alasita", "Carnaval de Oruro", "Fiesta de la Virgen de Urkupiña", "Festival del Sol"), 0),
            Question("¿Qué es la 'Pachamanca', un plato tradicional boliviano?", listOf("Un tipo de empanada", "Un guiso de carne y verduras cocido en piedra", "Un tipo de pastel de carne", "Un plato de sopa de maíz"), 1),
            Question("¿Cuál es el significado de la palabra 'Morenada' en la danza tradicional boliviana?", listOf("Danza de los morenos", "Danza de los caporales", "Danza de la tierra", "Danza de la victoria"), 0),
            Question("¿Qué símbolo es central en la celebración del Carnaval de Oruro?", listOf("El Diablo", "La Virgen de Urkupiña", "El Cóndor", "La Pachamama"), 0),
            Question("¿Qué tradición se sigue durante la Fiesta de la Alasita en Bolivia?", listOf("La compra de miniaturas para pedir deseos", "La realización de desfiles militares", "La danza de la Morenada", "La exhibición de artefactos precolombinos"), 0)

        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)

        currentLevel = intent.getIntExtra("LEVEL", 1)
        currentQuestions = allQuestions[currentLevel] ?: allQuestions[1]!!

        initViews()
        setUpListeners()
        loadQuestion()
        startTimer()
    }

    private fun initViews() {
        tvQuestion = findViewById(R.id.tvQuestion)
        tvQuestionCounter = findViewById(R.id.tvQuestionCounter)
        tvTimer = findViewById(R.id.tvTimer)
        progressBar = findViewById(R.id.progressBar)
        btnOption1 = findViewById(R.id.btnOption1)
        btnOption2 = findViewById(R.id.btnOption2)
        btnOption3 = findViewById(R.id.btnOption3)
        btnOption4 = findViewById(R.id.btnOption4)

        progressBar.max = currentQuestions.size
    }

    private fun setUpListeners() {
        val options = listOf(btnOption1, btnOption2, btnOption3, btnOption4)
        options.forEachIndexed { index, button ->
            button.setOnClickListener {
                checkAnswer(index)
            }
        }
    }

    private fun loadQuestion() {
        if (currentQuestionIndex < currentQuestions.size) {
            val question = currentQuestions[currentQuestionIndex]
            tvQuestion.text = question.text
            btnOption1.text = question.options[0]
            btnOption2.text = question.options[1]
            btnOption3.text = question.options[2]
            btnOption4.text = question.options[3]
            tvQuestionCounter.text = "Pregunta ${currentQuestionIndex + 1}/${currentQuestions.size}"
            progressBar.progress = currentQuestionIndex
        } else {
            finishQuiz()
        }
    }

    private fun checkAnswer(selectedIndex: Int) {
        val currentQuestion = currentQuestions[currentQuestionIndex]
        if (selectedIndex == currentQuestion.correctAnswerIndex) {
            score++
        }
        currentQuestionIndex++
        timer.cancel()
        loadQuestion()
        resetTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer((timeLeft * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeLeft = (millisUntilFinished / 1000).toInt()
                tvTimer.text = String.format("%02d:%02d", timeLeft / 60, timeLeft % 60)
            }

            override fun onFinish() {
                currentQuestionIndex++
                loadQuestion()
                resetTimer()
            }
        }.start()
    }

    private fun resetTimer() {
        timeLeft = 30
        startTimer()
    }

    private fun finishQuiz() {
        timer.cancel()
        val intent = Intent(this, ResultsActivity::class.java).apply {
            putExtra("SCORE", score)
            putExtra("TOTAL_QUESTIONS", currentQuestions.size)
            putExtra("LEVEL", currentLevel)
            // Añadir una bandera para evitar múltiples instancias
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        finish() // Asegúrate de que GameActivity se cierre
    }
}

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)