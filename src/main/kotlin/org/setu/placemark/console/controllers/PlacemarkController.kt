package org.setu.placemark.console.controllers

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkMemStore
import org.setu.placemark.console.models.PlacemarkModel
import org.setu.placemark.console.views.PlacemarkView
import org.setu.placemark.console.models.PlacemarkJSONStore

class PlacemarkController {
    //val placemarks = PlacemarkMemStore()
    val placemarks = PlacemarkJSONStore()
    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info("Launching Placemark Console App")
        println("Placemark Kotlin App Version 1.0")
    }

    fun menu() : Int {
        return placemarkView.menu()
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Placemark Console App" }
    }

    fun add(){
        val placemark = PlacemarkModel()
        if(placemarkView.addPlacemarkData(placemark))
            placemarks.create(placemark)
        else
            logger.info("Placemark Not Added")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {
        placemarkView.listPlacemarks(placemarks)
        var id = placemarkView.getId()
        var placemark: PlacemarkModel? = placemarks.findOne(id)
        placemarkView.updatePlacemarkData(placemark)
    }

    fun delete() {
        placemarkView.listPlacemarks(placemarks)
        var id = placemarkView.getId()
        val placemark: PlacemarkModel? = placemarks.findOne(id)

        if(placemark != null){
            placemarks.delete(placemark)
            println("Placemark Deleted...")
            placemarkView.listPlacemarks(placemarks)
        } else
            println("Placemark not deleted...")
    }

    fun search() {
        val id = placemarkView.getId()
        val placemark: PlacemarkModel? = placemarks.findOne(id)
        placemarkView.showPlacemark(placemark)
    }

    fun dummyData(){
        placemarks.create(PlacemarkModel(title = "New York New York", description = "So Good They Named It Twice"))
        placemarks.create(PlacemarkModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        placemarks.create(PlacemarkModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}