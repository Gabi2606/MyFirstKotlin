package com.myprojectkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyProjectKotlinApplication

fun main(args: Array<String>) {
	runApplication<MyProjectKotlinApplication>(*args)
}
