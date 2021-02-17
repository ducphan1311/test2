package com.example.androidkotlin

import java.io.Serializable

class Product : Serializable {
    private var id: Int = 0
    private var name: String =""
    private var price: Int = 0
    private var saleprice: Int =0
    private var avatar: String = ""
    private var description: String = ""
    private var categoryId: Int = 0

    constructor()
    constructor(id: Int, name: String, price: Int, saleprice: Int, avatar: String, description: String, categoryId: Int) {
        this.id = id
        this.name = name
        this.price = price
        this.saleprice = saleprice
        this.avatar = avatar
        this.description = description
        this.categoryId = categoryId
    }
    fun setId(id : Int){
        this.id = id
    }
    fun getId() : Int{
        return id
    }
    fun setName(name : String){
        this.name = name
    }
    fun getName() : String{
        return name
    }
    fun setPrice(price : Int){
        this.price = price
    }
    fun getPrice() : Int{
        return price
    }
    fun setSalePrice(saleprice : Int){
        this.saleprice = saleprice
    }
    fun getSalePrice() : Int{
        return saleprice
    }
    fun setAvatar(avatar: String){
        this.avatar = avatar
    }
    fun getAvatar() : String{
        return avatar
    }
    fun setDescription(description: String){
        this.description = description
    }
    fun getDescription() : String{
        return description
    }
    fun setCategoryId(categoryId: Int){
        this.categoryId = categoryId
    }
    fun getCategoryId() : Int{
        return categoryId
    }



    override fun toString(): String {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}'
    }

}
