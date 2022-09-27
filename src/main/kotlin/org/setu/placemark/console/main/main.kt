package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.controllers.PlacemarkController
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView

private val logger = KotlinLogging.logger {}

val placemarks = PlacemarkMemStore()
val placemarkView = PlacemarkView()
val controller = PlacemarkController()

fun main(args: Array<String>){
    logger.info {"Launching Placemark Console App"}
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = placemarkView.menu()
        when (input) {
            1 -> controller.add()
            2 -> controller.update()
            3 -> controller.list()
            4 -> controller.search()
            -99 -> controller.dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info {"Shutting Down Placemark Console App"}
}