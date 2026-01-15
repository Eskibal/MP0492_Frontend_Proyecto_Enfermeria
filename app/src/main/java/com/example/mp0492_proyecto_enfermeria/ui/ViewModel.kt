package com.example.mp0492_proyecto_enfermeria.ui

import androidx.lifecycle.ViewModel
import com.example.mp0492_proyecto_enfermeria.ui.data.sampleNurses
import com.example.mp0492_proyecto_enfermeria.ui.model.Nurse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class NurseUiState(
    val dynamicNurses: List<Nurse> = emptyList()
)

class NurseViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(NurseUiState())
    val uiState: StateFlow<NurseUiState> = _uiState.asStateFlow()

    // ✅ Lista unificada (mock + dinámicos)
    fun getAllNurses(): List<Nurse> = sampleNurses + uiState.value.dynamicNurses

    // ✅ Login (sin backend, validación local)
    fun validateLogin(user: String, password: String): Boolean {
        return getAllNurses().any { it.user == user && it.password == password }
    }

    // ✅ Búsqueda (sin backend)
    fun searchNurses(query: String): List<Nurse> {
        if (query.isBlank()) return emptyList()
        return getAllNurses().filter { it.name.contains(query, ignoreCase = true) }
    }

    fun registerNurse(name: String, user: String, email: String, password: String) {
        val newId = sampleNurses.size + _uiState.value.dynamicNurses.size + 1
        val newNurse = Nurse(newId, name, user, password, email)

        _uiState.update { it.copy(dynamicNurses = it.dynamicNurses + newNurse) }
    }
}