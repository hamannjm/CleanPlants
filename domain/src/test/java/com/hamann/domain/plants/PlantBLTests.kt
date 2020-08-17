package com.hamann.domain.plants

import com.hamann.domain.plants.mocks.PlantRepositoryMock
import com.hamann.domain.plants.models.MockPlantModel
import com.hamann.domain.usecases.GetPlantTask
import io.reactivex.schedulers.Schedulers
import org.junit.Test
import java.lang.IllegalArgumentException

class PlantBLTests {

    @Test
    fun verifyGetPlantParamChecking() {
        val mockRepository = PlantRepositoryMock()
        val mockPlant = MockPlantModel("1").also {
            it.species = "Test"
            it.commonName = "TestCommon"
        }
        mockRepository.addTestData(mockPlant)
        GetPlantTask(mockRepository, Schedulers.io(), Schedulers.io())
            .generateObservable(null)
            .subscribe({}, {
                assert(it is IllegalArgumentException)
            })
    }
}