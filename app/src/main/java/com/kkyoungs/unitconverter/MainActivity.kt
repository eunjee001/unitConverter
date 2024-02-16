package com.kkyoungs.unitconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.kkyoungs.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val inputEditText = binding.inputEditText
        val outputUnitTextView = binding.outputUnitTextView
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton
        var inputNumber : Int = 0

    // toInt는 MAXVALUE = 2147483647 --> 그 이상이 되면 crash
        inputEditText.addTextChangedListener {
            inputNumber = if (it.isNullOrEmpty()){
                0
            }else{
                it.toString().toInt()
            }

            // times는 곱하기 !
            if (cmToM){
                outputTextView.text = inputNumber.times(0.01).toString()

            }else{
                outputTextView.text = inputNumber.times(100).toString()

            }
        }
        swapImageButton.setOnClickListener {
            // not -> boolean의 역수 반환
            cmToM = cmToM.not()
            if (cmToM){
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            }else{
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()

            }
        }
    }

    // UI 상태 저장
    // 활동이 정지 되기 시작하면 인스턴스 상태 번들에  상태정보를 저장할 수 있도록 시스템이 onSaveInstanceState() 메서드 호출
    // 활동이 추가적인 인스턴스 상태정보를 저장하려면 onSaveInstanceState()를 재정의하고, 활동이 예상치 못하게 소멸될 경우 저장되는 Bundle 객체에 키-값 쌍을 추가해야 한다.
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState)
    }

    // 저장된 상태를 복구함
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        cmToM = savedInstanceState.getBoolean("cmToM")
        binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
    }
}