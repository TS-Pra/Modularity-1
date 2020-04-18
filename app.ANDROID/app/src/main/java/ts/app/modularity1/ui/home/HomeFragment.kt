package ts.app.modularity1.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.os.Environment.getExternalStorageDirectory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.arialyy.aria.core.Aria
import com.arialyy.aria.core.queue.DownloadTaskQueue
import com.github.florent37.runtimepermission.RuntimePermission.askPermission
import kotlinx.android.synthetic.main.fragment_home.*
import ts.app.modularity1.MainActivity
import ts.app.modularity1.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it

            DownLoad.setOnClickListener {
                Aria.download(this)
                    .load("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png")     //load download url
                    .setDownloadPath("/data/data/ts.app.modularity1/files/" + "googlelogo_color_272x92dp.png")    //file save path
                    .start();
            }
        })
        return root
    }
}
