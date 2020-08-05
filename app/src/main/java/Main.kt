import android.app.Application
import io.realm.Realm

class Main: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
    }
}