package brsenas.com.github.aluno_rm552250.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val local: String,
    val tipo: String,
    val impacto: String,
    val data: String,
    val afetados: Int
)