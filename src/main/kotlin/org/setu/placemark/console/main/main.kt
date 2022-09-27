package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

private val logger = KotlinLogging.logger {}
val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()

fun main(args: Array<String>){
    logger.info {"Launching Placemark Console App"}
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = placemarkView.menu()
        when (input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> placemarkView.listAllPlacemarks()
            4 -> searchPlacemark()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info {"Shutting Down Placemark Console App"}
}

fun addPlacemark() {
    val placemark = PlacemarkModel()

    if(placemarkView.addPlacementData(placemark)){
        placemarks.create(placemark)
    } else {
        logger.info("Placemark Not Added")
    }
}

fun updatePlacemark() {
    placemarkView.listAllPlacemarks()
    var id = placemarkView.getId()
    var placemark: PlacemarkModel? = placemarks.findOne(id)
    placemarkView.updatePlacemarkData(placemark)
}

fun searchPlacemark(){
    var id = placemarkView.getId()
    var placemark: PlacemarkModel? = placemarks.findOne(id)
    placemarkView.showPlacemark(placemark)
}

fun dummyData() {
    placemarks.create(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.create(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.create(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}