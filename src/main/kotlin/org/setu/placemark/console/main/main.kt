package org.setu.placemark.console.main

import org.setu.placemark.console.controllers.PlacemarkController

val controller = PlacemarkController()

fun main(args: Array<String>){
    controller.start()
}