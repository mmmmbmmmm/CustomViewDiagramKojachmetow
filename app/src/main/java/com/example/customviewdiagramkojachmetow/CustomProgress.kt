package com.example.customviewdiagramkojachmetow

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class CustomProgress(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {


    private val aPaint: Paint = Paint()
    private val bPaint: Paint = Paint()
    private val cPaint: Paint = Paint()
        //переменные, как значения для диаграммы
    var numOne = 200f
    var numTwo = 1120f
    var numThree = 2340f
        //общая сумма значений переменных
    var numSum = numOne + numTwo + numThree
            //переменная для размера текста (процентов)
    var numSize = 65
            //функция определяющая процентное соотношения из данных, для отрисовки диаграммы
    fun Calculation(indexOne: Float, sumNum: Float):Float{
        return (indexOne/sumNum) * 360f
    }
    //функция для получения процентов для текста
    fun CalculationTextProcent (num: Float) : Float {
        return (num/360) * 100
    }

//переменные для начала и размера каждой доли диаграммы( размер высчитываеться с помощью функции Calculation)
    private  var startAngleAOval = 0f
    private var sweepAngleAOval = Calculation(numOne,numSum)
    private var startAngleBOval = sweepAngleAOval
    private var sweepAngleBOval = Calculation(numTwo,numSum)
    private var startAngleCOval = sweepAngleBOval + sweepAngleAOval
    private var sweepAngleCOval = Calculation(numThree,numSum)
        // текст процентов
    var oneNum = CalculationTextProcent(sweepAngleAOval).toInt()
    var twoNum = CalculationTextProcent(sweepAngleBOval).toInt()
    var threeNum = CalculationTextProcent(sweepAngleCOval).toInt()
        //конструкторы
    constructor(context: Context?) : this(context, null) {}
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0) {}

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {//Отсновная функция для рисования по канвасу
        //работа с текстом
        aPaint.textSize = numSize.toFloat()
        bPaint.textSize = numSize.toFloat()
        cPaint.textSize = numSize.toFloat()
        //Назначение цвета
        aPaint.color = Color.parseColor("#C24346")
        bPaint.color = Color.parseColor("#C37C43")
        cPaint.color = Color.parseColor("#308D89")
        //вычисления овала с помощь метода RectF
        val x = ((width - height / 2) / 2).toFloat()
        val y = (height / 4).toFloat()
        val oval = RectF(x+50, y, width - x -50, height - y-100)
        //ОТРИСОВКА ДОЛЕЙ ДИОГРАММЫ (C помощью метода drawArc(аргументы : oval, стартовая позция и ее продолжительность , использование центра и цвет))
        canvas.drawArc(oval,startAngleAOval, sweepAngleAOval  , true, aPaint)
        canvas.drawArc(oval,startAngleBOval, sweepAngleBOval, true, bPaint)
        canvas.drawArc(oval, startAngleCOval, sweepAngleCOval, true, cPaint)
        //ОТРИСОВКА ТЕКСА (C помощью метода drawText(аргументы : строка, координаты по x и y и цвет))
        canvas.drawText(oneNum.toString() + "%", 60f,100f, aPaint)
        canvas.drawText(twoNum.toString() + "%", 60f, 200f, bPaint)
        canvas.drawText(threeNum.toString() + "%", 60f, 300f, cPaint)

    }

}