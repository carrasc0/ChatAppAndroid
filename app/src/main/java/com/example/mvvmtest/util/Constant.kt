package com.example.mvvmtest.util

class Constant {

    object SocketEvent {

        val NEW_MESSAGE = "newMessage"
        val TYPING = "typing"

    }

    object SocketKey {

        val FUNCTION_KEY = "func"
        val FN_KEY = "fn"
        val PARAMS_KEY = "params"

    }

    object Others {
        val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

    companion object {

        val SENDER = 1
        val NICKNAME = 2
        val TAG = "MVVM"
    }


}
