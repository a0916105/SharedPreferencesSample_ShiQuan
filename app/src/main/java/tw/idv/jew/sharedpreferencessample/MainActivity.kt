package tw.idv.jew.sharedpreferencessample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //AndroidX SharedPreferences
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        var value = preferences.getInt("MyKey", 0)

        /*val success = preferences.edit()    //取得寫入權限
                //設定修改資料
                .putInt("MyKey", value)
                .putInt("MyKey2", value+1)
                //將資料寫入disk，並回傳是否成功
                .commit()
                //.apply()  //先修改記憶體中的資料，再非同步地寫入disk，沒有回傳值可以確認是否成功*/
        //使用ktx專用語法精簡
        //精簡commit()的方式
        preferences.edit(commit = true){
            putInt("MyKey", value)
            putInt("MyKey2", value + 1)
        }
        //精簡apply()的方式，commit預設為false
        preferences.edit(){
            putInt("MyKey", value)
            putInt("MyKey2", value + 1)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.settings ->
                startActivity(Intent(this, SettingsActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}