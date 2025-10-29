package com.example.maquetas.io

import com.example.maquetas.exceptions.WrongFormatException
import com.example.maquetas.models.Project
import com.example.maquetas.models.ProjectObject

class ConfigString() {

    constructor(string:String):this(){
        if(string[0]!='@'){
            throw WrongFormatException()
        }else
        {
            value = string
        }
    }

    var value:String="@"
        private set

    fun addKey(key:String,value: String){
        val v=this.value
        this.value="$v$key:$value@"
    }

    fun addKey(key:String,obj: ProjectObject){
        val v=this.value
        val o=obj.objectName
        this.value="$v$key:$o@"

    }


    fun search(key:String):String?{//retorna null si no encuentra la key

        var k=key
        var value:String?

        if(k[0]!=('@'))
        {
            k= "@$k"
        }

        val regex=Regex(k)

        if(regex.containsMatchIn(this.value)) {
            val result = regex.find(this.value)!!
            val substring=this.value.substring(result.range.last)
            val subRegex=Regex("@")
            val subResult=subRegex.find(substring)!!

            value=substring.substring(2,subResult.range.last)


        }
        else{
            value=null
        }

        return value

    }

}