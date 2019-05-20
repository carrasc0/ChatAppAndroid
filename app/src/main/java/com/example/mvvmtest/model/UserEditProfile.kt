package com.example.mvvmtest.model

import com.example.mvvmtest.util.*

data class UserEditProfile(
        val images: Array<String>,
        val name: String,
        val gender: Gender,
        val age: String,
        val height: String,
        val profession: String,
        val job: String,
        val education: Education,
        val hometown: String,
        val kids: Kids,
        val family_plans: FamilyPlans,
        val drinking: Vices,
        val smoking: Vices
)