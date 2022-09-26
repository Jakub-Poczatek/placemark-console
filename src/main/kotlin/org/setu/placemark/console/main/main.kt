package org.setu.placemark.console.main

import mu.KotlinLogging
import org.setu.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}
val placemarks = ArrayList<PlacemarkModel>()

fun main(args: Array<String>){
    logger.info {"Launching Placemark Console App"}
    println("Placemark Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when (input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
            4 -> searchPlacemark()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info {"Shutting Down Placemark Console App"}
}

fun menu() : Int {
    var option : Int
    var input: String?

    println("Main Menu")
    println(" 1. Add Placemark")
    println(" 2. Update Placemark")
    println(" 3. List All Placemarks")
    println(" 4. Search Placemarks")
    println("-1. Exit")
    println()
    print("Enter Option: ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addPlacemark() {
    var placemark = PlacemarkModel()
    println("Add Placemark")
    println()
    print("Enter a Title: ")
    placemark.title = readLine()!!
    print("Enter a Description: ")
    placemark.description = readLine()!!
    if (placemark.title.isNotEmpty() && placemark.description.isNotEmpty()) {
        placemark.id = placemarks.size.toLong()
        placemarks.add(placemark.copy())
        logger.info("Placemark Added : [ $placemark ]")
    } else {
        logger.info("Placemark Not Added")
    }
}

fun updatePlacemark() {
    println("Update Placemark")
    println()
    listAllPlacemarks()
    var id = getId()
    var placemark: PlacemarkModel? = search(id)
    if(placemark == null){
        logger.info("Placemark doesn't exist")
    } else {
        println("Enter a new title for [ " + placemark.title + " ]: ")
        var title = readLine()!!
        println("Enter a new description for [ " + placemark.description + " ]: ")
        var description = readLine()!!
        if(title.isNotEmpty() && description.isNotEmpty()) {
            placemark.title = title
            placemark.description = description
            logger.info("You updated [ " + placemark.title + " ] for title and [ " + placemark.description + " ] for description")
        } else {
            logger.info("Placemark not updated")
        }
    }
}

fun listAllPlacemarks() {
    println("List All Placemarks")
    println()
    placemarks.forEach {
        println("${it}")
    }
    println()
}

fun searchPlacemark(){
    var searchId = getId()
    var placemark: PlacemarkModel? = search(searchId)
    if (placemark == null){
        logger.info("Placemark does not exist...")
    } else {
        println("Placemark Details: $placemark")
    }
}

fun getId(): Long {
    var strId: String?
    var searchId: Long
    print("Enter id to Search/Update: ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && strId.isNotEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id}
    return foundPlacemark
}

fun dummyData() {
    placemarks.add(PlacemarkModel(1, "New York New York", "So Good They Named It Twice"))
    placemarks.add(PlacemarkModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    placemarks.add(PlacemarkModel(3, "Waterford City", "You get great Blaas Here!!"))
}