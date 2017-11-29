package ng.name.amustapha.samuel

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import ng.name.amustapha.samuel.fragments.*
import ng.name.amustapha.samuel.utils.Config
import ng.name.amustapha.samuel.utils.defaults
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var config: Config
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        softReplace(TodayFragment())
        config = Config(this)

        if (!config.has("defaults-loaded")){
            defaults().saveDefaults()
            config.set("defaults-loaded", "")
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        (nav_view.getHeaderView(0).findViewById<CircleImageView>(R.id.imageView)).setImageBitmap(config.getPicture())
        (nav_view.getHeaderView(0).findViewById<TextView>(R.id.main)).setText(config.get("name"))
        (nav_view.getHeaderView(0).findViewById<TextView>(R.id.sub)).setText(config.get("email"))

        val cal = Calendar.getInstance()
        Log.e("MILLISECONDS", "" + cal.get(Calendar.MILLISECOND))
        Log.e("SECONDS", "" + cal.get(Calendar.SECOND))
        Log.e("MINUTES", "" + cal.get(Calendar.MINUTE))


        val intent = Intent(Context.ALARM_SERVICE)
        sendBroadcast(intent)


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_schedule -> {
                replace(ScheduleFragment())
            }

            R.id.nav_home ->{
                replace(TodayFragment())
            }
            R.id.nav_schedules -> {
                replace(SchedulesFragment())
            }
            R.id.nav_category->{
                replace(CategoriesFragment())
            }
            R.id.nav_add_category->{
                val frag = CategoryFragment()
                frag.show(supportFragmentManager, "n")
            }
            R.id.nav_contact -> {
                replace(ContactFragment())
            }
            R.id.nav_feedback ->{
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(android.content.Intent.EXTRA_EMAIL, arrayOf("Samuel Noah <samexine@gmail.com>"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feeback")

                val pm = packageManager
                val matches = pm.queryIntentActivities(intent, 0)
                var best: ResolveInfo? = null
                for (info in matches)
                    if (info.activityInfo.packageName.endsWith(".gm") || info.activityInfo.name.toLowerCase().contains("gmail"))
                        best = info
                if (best != null)
                    intent.setClassName(best.activityInfo.packageName, best.activityInfo.name)
                startActivity(intent)
            }
            R.id.nav_share -> {
                val share = Intent(android.content.Intent.ACTION_SEND)
                share.type = "text/plain"
                share.putExtra(Intent.EXTRA_TEXT, "Student personal assistant is an app that helps students schedule their day. Get it from Samuel Noah<samexine@gmail.com>")
                startActivity(share)
            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replace(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit()
    }

    fun softReplace(fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

    }
}
