package com.hamann.local.plants

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hamann.local.creators.PlantCreator
import com.hamann.local.models.PlantModel
import com.hamann.local.source.LocalDataSourceImpl
import io.reactivex.schedulers.Schedulers
import io.realm.*
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class PlantRealmTests {

    companion object {
        private val plants = listOf(
            PlantModel().also {
                it.identifier = "1"
                it.species = "Monstera adonsonii"
                it.commonName = "Swiss Cheese Plant"
            },
            PlantModel().also {
                it.identifier = "2"
                it.species = "Zamioculcas zamiifolia"
                it.commonName = "ZZ Plant"
            },
            PlantModel().also {
                it.identifier = "3"
                it.species = "Pachira glabra"
                it.commonName = "Money tree"
            },
            PlantModel().also {
                it.identifier = "4"
                it.species = "Nephrolepis exaltata"
                it.commonName = "Boston fern"
            })
    }

    private lateinit var mockRealm: Realm
    private lateinit var testConfiguration: RealmConfiguration

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Realm.init(context)
        testConfiguration = RealmConfiguration.Builder()
            .inMemory()
            .name("unitTest-realm")
            .build()
        mockRealm = Realm.getInstance(testConfiguration)
        mockRealm.executeTransaction { realm ->
            plants.forEach { plant ->
                realm.insert(plant)
            }
        }
    }

    @After
    fun teardown() {
        mockRealm.close()
        Realm.deleteRealm(testConfiguration)
    }

    @Test
    fun canCreatePlant() {
        val creator = PlantCreator(mockRealm)
        val plant = creator.newManagedPlant()
        assertFalse(plant.identifier.isNullOrEmpty())
        assertTrue(plant.isManaged)
    }

    @Test
    fun canFindPlant() {
        val localSource = LocalDataSourceImpl(
            mockRealm,
            PlantCreator((mockRealm)),
            Schedulers.io()
        )
    }
}