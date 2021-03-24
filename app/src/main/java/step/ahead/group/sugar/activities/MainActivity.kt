package step.ahead.group.sugar.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import github.com.st235.lib_expandablebottombar.ExpandableBottomBar
import kotlinx.android.synthetic.main.activity_main.*
import step.ahead.group.sugar.R
import step.ahead.group.sugar.fragments.MainFragment
import step.ahead.group.sugar.utils.FragmentUtils
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.primaryConstructor


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomBar: ExpandableBottomBar = expandable_bottom_bar

        /*bottomBar.addItems(
            ExpandableBottomBarMenuItem.Builder(context = this)
                .addItem(R.id.first_fragment, R.drawable.ic_baseline_dashboard_24, R.string.app_name, Color.GRAY)
                .addItem(R.id.second_fragment, R.drawable.ic_baseline_dashboard_24, R.string.app_name, Color.YELLOW)
                .build()
        )*/
        bottomBar.select(R.id.step_ahead_group_sugar_fragments_MainFragment)
        FragmentUtils().open(this, MainFragment())
        bottomBar.onItemSelectedListener = { view, menuItem ->
            val className = resources.getResourceEntryName(menuItem.itemId);
            val kClass = Class.forName(className).kotlin
            val instance = kClass.objectInstance ?: kClass.java.newInstance()
            if (instance is Fragment){
                FragmentUtils().open(this, instance)
            }
        }

        bottomBar.onItemReselectedListener = { view, menuItem ->
            Log.d("ReselectedListener", resources.getResourceEntryName(menuItem.itemId))
        }

    }

}