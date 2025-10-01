package com.example.maquetas.io

import com.example.maquetas.exceptions.WrongFormatException

class StringFormatter {

    fun getStringValues(str:String):List<String>{

        return emptyList()//TODO
    }

    fun searchValue(key:String,str:String):String{

        if(key[0]!=('@'))
        {
            throw WrongFormatException("Wrong format: '@' char was expected at string[0]")
        }

        return ""

    }

}