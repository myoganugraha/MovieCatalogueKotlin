package itk.myoganugraha.moviecataloguekotlin.Activity

import android.content.Intent
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.Menu
import android.view.MenuItem
import com.miguelcatalan.materialsearchview.MaterialSearchView
import itk.myoganugraha.moviecataloguekotlin.Fragment.NowPlayingFragment
import itk.myoganugraha.moviecataloguekotlin.Fragment.UpcomingFragment
import itk.myoganugraha.moviecataloguekotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbarMainActivity)
        tabsMainActivity.setupWithViewPager(viewpagerMainActivity)

        setupViewPager(viewpagerMainActivity)

        searchViewMainActivity.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
                intent.putExtra("query", query)
                startActivity(intent)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(NowPlayingFragment(), getString(R.string.tab1_title))
        adapter.addFragment(UpcomingFragment(), getString(R.string.tab2_title))
        viewPager.adapter = adapter
    }

   override fun onCreateOptionsMenu(menu: Menu): Boolean{
       menuInflater.inflate(R.menu.action_menu, menu)

       val item = menu.findItem(R.id.action_search)
       searchViewMainActivity.setMenuItem(item)

       return true
   }

    internal inner class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        override fun getCount(): Int {
           return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String){
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int) : CharSequence?{
            return mFragmentTitleList.get(position)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.english){
            val appLocale = Locale("en")
            Locale.setDefault(appLocale)

            val appConfig = Configuration()
            appConfig.locale = appLocale

            resources.updateConfiguration(appConfig, resources.displayMetrics)
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }
        else if (item?.itemId == R.id.indonesia){
            val appLocale = Locale("in")
            Locale.setDefault(appLocale)

            val appConfig = Configuration()
            appConfig.locale = appLocale

            resources.updateConfiguration(appConfig, resources.displayMetrics)
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        return super.onOptionsItemSelected(item)
    }
}
