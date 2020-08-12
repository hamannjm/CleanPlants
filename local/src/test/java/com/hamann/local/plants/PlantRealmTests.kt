package com.hamann.local.plants

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.hamann.local.models.PlantModel
import io.realm.*
import io.realm.internal.RealmCore
import io.realm.log.RealmLog
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.powermock.api.mockito.PowerMockito.*
import org.powermock.core.classloader.annotations.PowerMockIgnore
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.modules.junit4.PowerMockRunnerDelegate
import org.powermock.modules.junit4.rule.PowerMockRule
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


@RunWith(PowerMockRunner::class)
@PowerMockRunnerDelegate(RobolectricTestRunner::class)
@Config(sdk = [21])
@PowerMockIgnore(value = ["org.mockito.*", "org.robolectric.*", "android.*"])
@SuppressStaticInitializationFor("io.realm.internal.Util")
@PrepareForTest(value = [Realm::class, RealmConfiguration::class, RealmQuery::class, RealmResults::class, RealmCore::class, RealmLog::class])
class PlantRealmTests {

    @Rule
    val rule = PowerMockRule()

    private lateinit var mockRealm: Realm

    @Before
    fun setup() {
        mockStatic(RealmCore::class.java)
        mockStatic(RealmLog::class.java)
        mockStatic(Realm::class.java)
        mockStatic(RealmConfiguration::class.java)
        Realm.init(ApplicationProvider.getApplicationContext())

        mockRealm = mock(Realm::class.java)
        val mockRealmConfig = mock(RealmConfiguration::class.java)

        doNothing().`when`(RealmCore::class.java)
        RealmCore.loadLibrary(any(Context::class.java))

        whenNew(RealmConfiguration::class.java).withAnyArguments().thenReturn(mockRealmConfig)

        `when`(Realm.getDefaultInstance()).thenReturn(mockRealm)

        `when`(mockRealm.createObject(PlantModel::class.java)).thenReturn(PlantModel())
    }


//    private fun <T : RealmObject?> mockRealmQuery(): RealmQuery<T>? {
//        return mock(RealmQuery::class.java)
//    }
//
//    private fun <T : RealmObject?> mockRealmResults(): RealmResults<T>? {
//        return mock(RealmResults::class.java)
//    }
}