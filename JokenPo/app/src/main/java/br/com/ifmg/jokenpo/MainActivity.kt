package br.com.ifmg.jokenpo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import br.com.ifmg.jokenpo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // receives all layout components
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        // select the components on the screen, starting from the project root
        setContentView(this.binding.root)
        eventListener()
    }

    // eventListener identifies clicks on images and coordinates subsequent actions
    private fun eventListener(){
        this.binding.tesoura.setOnClickListener(this)
        this.binding.pedra.setOnClickListener(this)
        this.binding.papel.setOnClickListener(this)
        this.binding.nova.setOnClickListener(this)
    }

    /*
        onClick is an overwritten method from the OnClickListener interface
        will be responsible for interacting after clicks
    */
    override fun onClick(clickableComponent: View?) {
        if (clickableComponent != null) {
            when(clickableComponent.id){
                binding.tesoura.id ->{
                    this.setScissorsChoice()
                    this.setCpuChoice()
                }
                binding.pedra.id ->{
                    this.setStoneChoice()
                    this.setCpuChoice()
                }
                binding.papel.id ->{
                    this.setPapperChoice()
                    this.setCpuChoice()
                }
                binding.nova.id ->{
                    this.setResetChoices()
                }
            }
            setRating()
        }
    }

    private fun setScissorsChoice(){
        this.binding.minhaEscolha.text = "TESOURA"
    }

    private fun setStoneChoice(){
        this.binding.minhaEscolha.text = "PEDRA"
    }

    private fun setPapperChoice(){
        this.binding.minhaEscolha.text = "PAPEL"
    }

    private fun setCpuChoice(){
        val options: Array<String> = arrayOf("TESOURA", "PEDRA", "PAPEL")
        val random = java.util.Random()
        this.binding.escolhaCpu.text = options[random.nextInt(options.size)]
    }

    // sets scores based on game rules
    @SuppressLint("SetTextI18n")
    private fun setRating(){
        val cpu: CharSequence = binding.escolhaCpu.text
        val me: CharSequence = binding.minhaEscolha.text
        val score: CharSequence = binding.placar.text
        var meValue: Int = score.split(" ")[0].toInt()
        var cpuValue: Int = score.split(" ")[2].toInt()
        if (cpu == "TESOURA" && me == "PAPEL"){
            cpuValue++
        }else if(cpu == "PAPEL" && me == "TESOURA"){
            meValue++
        }
        if (cpu == "PAPEL" && me == "PEDRA"){
            cpuValue++
        }else if(cpu == "PEDRA" && me == "PAPEL"){
            meValue++
        }
        if (cpu == "PEDRA" && me == "TESOURA"){
            cpuValue++
        }else if(cpu == "TESOURA" && me == "PEDRA"){
            meValue++
        }

        this.binding.placar.text = "$meValue : $cpuValue"
    }

    private fun setResetChoices(){
        this.binding.minhaEscolha.text = ""
        this.binding.escolhaCpu.text = ""
    }
}