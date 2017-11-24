package ng.name.amustapha.samuel

import android.Manifest
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.ETC1.encodeImage
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import android.view.View
import android.widget.ImageView

import kotlinx.android.synthetic.main.content_first.*
import me.riddhimanadib.formmaster.FormBuilder
import me.riddhimanadib.formmaster.model.BaseFormElement
import me.riddhimanadib.formmaster.model.FormElementTextEmail
import me.riddhimanadib.formmaster.model.FormElementTextSingleLine
import me.riddhimanadib.formmaster.model.FormHeader
import ng.name.amustapha.samuel.utils.Config
import java.io.ByteArrayOutputStream

class FirstActivity : AppCompatActivity() {
lateinit var config: Config
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        config = Config(this)
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 0)

        if(!config.get("name").equals("blank")){
            val intent = Intent(this, AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }

        picture.setOnClickListener({
            val imagePicker = Intent()
            imagePicker.action = Intent.ACTION_GET_CONTENT
            imagePicker.type = "image/*"

            startActivityForResult(imagePicker, 0)
        })

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

            val intent = Intent(this, AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)


        })


    }

    private fun askForPermission(permission: String, requestCode: Int?) {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {

                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode!!)

            } else {

                ActivityCompat.requestPermissions(this, arrayOf(permission), requestCode!!)
            }
        } else {
            //            Toast.makeText(getContext(), "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 0) {

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0) {
            try {
                val selectedImage = data!!.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val cursor = this.getContentResolver().query(
                        selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()

                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                val filePath = cursor.getString(columnIndex)
                cursor.close()

                setImage(BitmapFactory.decodeFile(filePath))
            } catch (e: Exception) {

            }

        }
    }

    fun setImage(image: Bitmap) {
        picture.setImageBitmap(image)
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val b = baos.toByteArray()
        val encImage = Base64.encodeToString(b, Base64.DEFAULT)

        config.set("picture", encImage)

    }

}
