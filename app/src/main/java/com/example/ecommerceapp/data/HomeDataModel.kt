package com.example.ecommerceapp.data

class HomeDataModel {
    var type:Int=-1
    lateinit var gridlayoutProduct:MutableList<GridProductModelClass>
    lateinit var stripAdvData:StripAdvDataModel
    lateinit var bannerData:MutableList<BannerDataModel>

    constructor(data:StripAdvDataModel,type:Int){
        this.stripAdvData=data
        this.type=type//1
    }
    constructor(data:MutableList<Any>,type:Int){

        when(type){
            0->{
                this.gridlayoutProduct=data as MutableList<GridProductModelClass>
                this.type=type//0
            }
            2->{
                this.bannerData=data as MutableList<BannerDataModel>
                this.type=type//2
            }
        }

    }


}