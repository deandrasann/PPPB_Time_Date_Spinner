package com.example.tugaspertemuan7

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tugaspertemuan7.databinding.ActivityMainBinding
import com.example.tugaspertemuan7.databinding.ActivitySecondBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class secondActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var binding : ActivitySecondBinding
    private val calendar = Calendar.getInstance()
    private var selectedDate: String = ""
    private var selectedTime: String = ""
    private var selectedRepeat: String = ""

    fun showCalendar(view: View) {
        val datePickerDialog = DatePickerDialog(
            this, this,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            val stringRepeat = resources.getStringArray(R.array.stringRepeat)
            val adapterSpinnerRepeat = ArrayAdapter(
                this@secondActivity,  // Correctly reference the activity context
                android.R.layout.simple_spinner_dropdown_item,
                stringRepeat
            )
            spinnerRepeat.adapter = adapterSpinnerRepeat

            spinnerRepeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    // Corrected array access
                    selectedRepeat = stringRepeat[p2]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
            val selection = "Once"
            val spinnerPosition: Int = adapterSpinnerRepeat.getPosition(selection)
            binding.spinnerRepeat.setSelection(spinnerPosition)
        }

    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val dateFormat = SimpleDateFormat("dd-MMMM-yyyy", Locale.getDefault())
        selectedDate = dateFormat.format(calendar.time)

        binding.textDate.text = selectedDate
    }
    fun showTimepicker(view: View) {
        val timePickerDialog = TimePickerDialog(
            this, this,
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            true
        )
        timePickerDialog.show()
    }

    override fun onTimeSet(p0: TimePicker?,hourOfDay: Int, minute: Int) {
        selectedTime = String.format("%02d:%02d", hourOfDay, minute)
        binding.editTime.text = selectedTime
    }
    fun showAlert(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("SimpliRemind")
        builder.setMessage("Do you want add this as new task")
        builder.setPositiveButton("Yes"){
                dialog, _ ->
            val intent = Intent(this, ThirdActivity::class.java)
            val title = binding.editTitle.text.toString()
            intent.putExtra("SELECTED_DATE", selectedDate)
            intent.putExtra("SELECTED_TIME", selectedTime)
            intent.putExtra("SELECTED_REPEAT", selectedRepeat)
            intent.putExtra("TITLE", title)
            startActivity(intent)
        }
        builder.setNeutralButton("No"){
                dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }
}