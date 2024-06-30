package com.dedicoder.common.library.config

interface S3Config {
    val accessKey: String
    val secretKey: String
    val region: String
    val bucketName: String
}