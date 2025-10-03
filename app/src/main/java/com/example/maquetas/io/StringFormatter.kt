package com.example.maquetas.io

import com.example.maquetas.exceptions.WrongFormatException

class ConfigString() {

    constructor(string:String):this(){
        if(string[0]!='@'){
            throw WrongFormatException()
        }else
        {
            str = string
        }
    }

    var str:String="@"
        private set

    fun addKey(key:String,value: String){
        str="$str$key:$value@"
    }

    fun search(key:String):String?{//retorna null si no encuentra la key

        var k=key
        val regex=Regex(k)
        var value:String?


        if(k[0]!=('@'))
        {
            k="@"+k
        }


        if(regex.matches(k)) {
            val result = regex.find(str)!!
            val substring=str.substring(result.range.last)
            val subRegex=Regex("@")
            val subResult=subRegex.find(substring)!!

            value=substring.substring(0,subResult.range.last)


        }
        else{
            value=null
        }

        return value

    }

}