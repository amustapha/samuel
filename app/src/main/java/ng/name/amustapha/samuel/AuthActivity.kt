package ng.name.amustapha.samuel

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_auth.*
import ng.name.amustapha.samuel.fragments.SetPasswordFragment
import ng.name.amustapha.samuel.fragments.SigninFragment
import ng.name.amustapha.samuel.utils.Config

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val config = Config(this)
        var fragment : Fragment = SetPasswordFragment()
        if (config.has("password")){
           fragment = SigninFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.container,  fragment).commit()
    }

}
