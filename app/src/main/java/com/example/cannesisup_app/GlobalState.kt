package com.example.cannesisup_app

data class TripInfo(
    var budget: String,
    var selectedChips: List<String>,
    var startDate: String,
    var endDate: String,
    var groupSize: String,
    var town: String
)

object GlobalState {
    var trips: MutableList<TripInfo> = mutableListOf()
    var username: String = ""
}