package ng.name.amustapha.samuel

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.content_first.*
import me.riddhimanadib.formmaster.FormBuilder
import me.riddhimanadib.formmaster.model.BaseFormElement
import me.riddhimanadib.formmaster.model.FormElementTextEmail
import me.riddhimanadib.formmaster.model.FormElementTextSingleLine
import me.riddhimanadib.formmaster.model.FormHeader
import ng.name.amustapha.samuel.utils.Config

class FirstActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        val config = Config(this)

        if(!config.get("name").equals("blank")){
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


        val formBuilder = FormBuilder(this, aboutForm)
        val header = FormHeader.createInstance("Who are you?")
        val name = FormElementTextSingleLine.createInstance().setTitle("Name").setHint("Full name please").setRequired(true)
        val email = FormElementTextEmail.createInstance().setTitle("Email").setHint("Email address").setRequired(true)

        val formFields = ArrayList<BaseFormElement>()
        formFields.addAll(arrayOf(header, name, email))
        formBuilder.addFormElements(formFields)
        proceed.setOnClickListener(View.OnClickListener {
            config.set("name", name.value)
            config.set("email", email.value)

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        })


    }

}
