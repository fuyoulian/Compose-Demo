package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {

    private val desList by lazy {
        arrayListOf(
            arrayOf("AC", "()", "%", "/"),
            arrayOf("7", "8", "9", "*"),
            arrayOf("4", "5", "6", "-"),
            arrayOf("1", "2", "3", "+"),
            arrayOf("0", ".", "De", "=")
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                initView()
            }
        }
    }
    @Composable
    @Preview(showBackground = true)
    private fun initView() {
        val viewmodel = viewModel<CalculatorViewModel>()
        Column(
            Modifier.fillMaxSize().background(Color.Gray),
            Arrangement.Bottom
        ) {
            Text(
                text = viewmodel.state.leftNumber + (viewmodel.state.operator?.des ?: "") + viewmodel.state.rightNumber,
                textAlign = TextAlign.End,
                fontSize = 70.sp,
                lineHeight = 70.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            )
            desList.forEach {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    it.forEach {
                        UiUtil.ProduceButton(
                            modifier = Modifier.weight(1f).aspectRatio(1f).background(Color.White),
                            description = it,
                            onClick = {
                            when(it) {
                                "0" -> viewmodel.onEvents(CalculatorEvent.Number(0))
                                "1" -> viewmodel.onEvents(CalculatorEvent.Number(1))
                                "2" -> viewmodel.onEvents(CalculatorEvent.Number(2))
                                "3" -> viewmodel.onEvents(CalculatorEvent.Number(3))
                                "4" -> viewmodel.onEvents(CalculatorEvent.Number(4))
                                "5" -> viewmodel.onEvents(CalculatorEvent.Number(5))
                                "6" -> viewmodel.onEvents(CalculatorEvent.Number(6))
                                "7" -> viewmodel.onEvents(CalculatorEvent.Number(7))
                                "8" -> viewmodel.onEvents(CalculatorEvent.Number(8))
                                "9" -> viewmodel.onEvents(CalculatorEvent.Number(9))
                                "+" -> viewmodel.onEvents(CalculatorEvent.Operation(CalculatorOperation.Add))
                                "-" -> viewmodel.onEvents(CalculatorEvent.Operation(CalculatorOperation.Subtract))
                                "*" -> viewmodel.onEvents(CalculatorEvent.Operation(CalculatorOperation.Multiply))
                                "/" -> viewmodel.onEvents(CalculatorEvent.Operation(CalculatorOperation.Divide))
                                "De" -> viewmodel.onEvents(CalculatorEvent.Delete)
                                "AC" -> viewmodel.onEvents(CalculatorEvent.Clear)
                                "=" -> viewmodel.onEvents(CalculatorEvent.Calculator)
                                "." -> viewmodel.onEvents(CalculatorEvent.Decimal)
                            }
                        })
                    }
                }
            }
        }
    }
}
