package com.example.retrofit

data class ResponseProjectModel(
    val projects: List<ProjectModel>
)

data class ProjectModel(
    var name:String,
    var id:Int
)
