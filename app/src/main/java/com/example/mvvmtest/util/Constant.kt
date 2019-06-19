package com.example.mvvmtest.util

class Constant {

    object SocketEvent {

        const val NEW_MESSAGE = "newMessage"
        const val TYPING = "typing"

    }

    object SocketKey {

        const val FUNCTION_KEY = "func"
        const val FN_KEY = "fn"
        const val PARAMS_KEY = "params"

    }

    object Others {
        const val REQUEST_PERMISSIONS_REQUEST_CODE = 34
    }

    companion object {

        const val SENDER = 2
        const val NICKNAME = 1
        const val TAG = "MVVM"
    }


}
