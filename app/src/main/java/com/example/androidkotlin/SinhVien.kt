package com.example.androidkotlin

import java.io.Serializable

class SinhVien : Serializable {
    private var hoTen : String = ""
    private var diaChi : String = ""
    private var namSinh : Int = 0

    constructor(hoTen: String, diaChi: String, namSinh: Int) {
        this.hoTen = hoTen
        this.diaChi = diaChi
        this.namSinh = namSinh
    }

    fun setHoTen(ht : String){
        hoTen = ht
    }
    fun getHoTen() : String{
        return hoTen
    }
    fun setDiaChi(dc : String){
        diaChi = dc
    }
    fun getDiaChi() : String{
        return diaChi
    }
    fun setNamSinh(ns : Int){
        namSinh = ns
    }
    fun getNamSinh() : Int{
        return namSinh
    }

    override fun toString(): String {
        return "SinhVien(hoTen='$hoTen', diaChi='$diaChi', namSinh=$namSinh)"
    }


}