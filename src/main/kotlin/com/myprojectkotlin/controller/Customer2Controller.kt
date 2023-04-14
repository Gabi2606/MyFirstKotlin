package com.myprojectkotlin.controller

import com.myprojectkotlin.controller.request.PostCustomerRequest
import com.myprojectkotlin.controller.request.PutCustomerRequest
import com.myprojectkotlin.model.Customer2Model
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("customer")
class Customer2Controller {

    val customers = mutableListOf<Customer2Model>()

    @GetMapping
    fun getAll(): List<Customer2Model>{
        return customers
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer:PostCustomerRequest ){

        val id = if(customers.isEmpty()){
            1
        } else{
            customers.last().id.toInt() + 1
        }.toString()
        customers.add(Customer2Model(id, customer.name, customer.email))
    }

    @GetMapping("/{id}")
    fun getCustomer (@PathVariable id:String): Customer2Model{
       return customers.filter { it.id == id }.first()
    //si el registro es igual de la url, es lo que quiero
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update (@PathVariable id:String, @RequestBody customer: PutCustomerRequest){
        customers.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }
        //accesa el customer a partir del it
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete (@PathVariable id:String){
        customers.removeIf { it.id == id }
}
}