package com.wms.wms.data.model

class User(
    var username: String,
    var accessToken: String,
    var expireAt: Long,
    var cookie: String,
) {}
