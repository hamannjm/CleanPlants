package com.hamann.local.plants

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.hamann.local.creators.PlantCreator
import com.hamann.local.models.Plant
import com.hamann.local.models.PlantModel
import com.hamann.local.plants.models.TestPlantEntityImpl
import com.hamann.local.source.LocalDataSourceImpl
import io.reactivex.schedulers.Schedulers
import io.realm.*
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantRealmTests {

    companion object {
        private val plants = listOf(
            TestPlantEntityImpl("1").also {
                it.species = "Monstera adonsonii"
                it.commonName = "Swiss Cheese Plant"
            },
            TestPlantEntityImpl("2").also {
                it.species = "Zamioculcas zamiifolia"
                it.commonName = "ZZ Plant"
            },
            TestPlantEntityImpl("3").also {
                it.species = "Pachira glabra"
                it.commonName = "Money tree"
            },
            TestPlantEntityImpl("4").also {
                it.species = "Nephrolepis exaltata"
                it.commonName = "Boston fern"
            })
    }

    private lateinit var mockRealm: Realm
    private lateinit var testConfiguration: RealmConfiguration

    //Need to get access to io.realm.rule for the RunInLooperThread functionality...
//    @Rule
//    val looperRule = RunInLooperThread



    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Realm.init(context)
        testConfiguration = RealmConfiguration.Builder()
            .inMemory()
            .name("unitTest-realm")
            .build()
        mockRealm = Realm.getInstance(testConfiguration)
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
    fun canCreatePlant_CustomID() {
        val creator = PlantCreator(mockRealm)
        val testPlant = plants.first()
        val managedPlant = creator.newManagedPlant(testPlant.identifier)
        assertTrue(managedPlant.isManaged)
        assertTrue(managedPlant.identifier == testPlant.identifier)
    }

    @Test
    fun canModifyPlant() {
        val creator = PlantCreator(mockRealm)
        val plant = creator.create()
        val plantToSave = plants.first()
        plant.commonName = plantToSave.commonName
        plant.species = plantToSave.species
    }

    @Test
    fun canFindPlant() {
        val creator = PlantCreator(mockRealm)
        plants.forEach { plant ->
            val managedPlant = creator.newManagedPlant(plant.identifier)
            Plant(managedPlant).also {
                it.commonName = plant.commonName
                it.species = plant.species
            }
        }
        val plantToFind = plants[1]
        val localSource = LocalDataSourceImpl(
            mockRealm,
            creator
        )
        localSource.getPlant(plantToFind.identifier)
            .subscribeOn(Schedulers.io())
            .subscribe { plant ->
                assertTrue(plant.identifier == plantToFind.identifier)
            }
    }
}