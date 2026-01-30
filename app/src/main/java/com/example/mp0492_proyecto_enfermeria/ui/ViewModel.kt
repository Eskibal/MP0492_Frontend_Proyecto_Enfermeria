package com.example.mp0492_proyecto_enfermeria.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mp0492_proyecto_enfermeria.ui.data.sampleNurses
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class NurseUiState(
    val dynamicNurses: List<Nurse> = emptyList()
)

class NurseViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(NurseUiState())
    val uiState: StateFlow<NurseUiState> = _uiState.asStateFlow()

    // Lista unificada (mock + dinámicos)
    fun loadNursesFromBackend(): List<Nurse> {
        viewModelScope.launch {
            try {
                val nursesFromApi = RemoteConnection.endPoints.getAll()
                _uiState.update { it.copy(dynamicNurses = nursesFromApi) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return uiState.value.dynamicNurses
    }

    // Para que el backend pueda “inyectar” la lista al ViewModel
    fun setDynamicNurses(nurses: List<Nurse>) {
        _uiState.update { it.copy(dynamicNurses = nurses) }
    }

    // Login
    fun validateLogin(user: String, password: String, result: (Boolean) -> Unit){
        viewModelScope.launch {
            try {
                val response = RemoteConnection.endPoints.login(Nurse(user = user, password = password))

                result(response.isSuccessful && response.body() == true)
            } catch(e: Exception) {
                result(false)
                e.printStackTrace()
            }
        }
    }

    // Búsqueda
    fun searchNurses(query: String): List<Nurse> {

        if (query.isBlank()) return emptyList()

        // Lanzamos la petición en segundo plano
        viewModelScope.launch {
            try {
                val response =
                    RemoteConnection.endPoints.findByName(query)

                if (response.isSuccessful && response.body() != null) {
                    _uiState.update {
                        it.copy(
                            dynamicNurses = listOf(response.body()!!)
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(dynamicNurses = emptyList())
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(dynamicNurses = emptyList())
                }
            }
        }
        // IMPORTANTE:
        // devolvemos lo que haya en el estado (Compose se recompone)
        return uiState.value.dynamicNurses
    }

    // Register
    fun registerNurse(
        name: String,
        user: String,
        email: String,
        password: String,
        result: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val newNurse = Nurse(
                    name = name,
                    user = user,
                    password = password,
                    email = email
                )

                val response = RemoteConnection.endPoints.createNurse(newNurse)

                if (response.isSuccessful) {
                    _uiState.update { it.copy(dynamicNurses = it.dynamicNurses + newNurse) }
                    result(true)
                } else {
                    result(false)
                }
            } catch (e: Exception) {
                result(false)
                e.printStackTrace()
            }
        }
    }
}
