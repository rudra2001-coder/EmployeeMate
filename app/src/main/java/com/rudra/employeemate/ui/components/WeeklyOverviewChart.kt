package com.rudra.employeemate.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

@Composable
fun WeeklyOverviewChart() {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            val chart = BarChart(it)
            val entries = listOf(
                BarEntry(0f, 3f),
                BarEntry(1f, 5f),
                BarEntry(2f, 2f),
                BarEntry(3f, 8f),
                BarEntry(4f, 6f),
                BarEntry(5f, 4f),
                BarEntry(6f, 7f),
            )

            val dataSet = BarDataSet(entries, "Weekly Overview")
            val data = BarData(dataSet)

            chart.data = data
            chart.invalidate()
            chart
        }
    )
}
