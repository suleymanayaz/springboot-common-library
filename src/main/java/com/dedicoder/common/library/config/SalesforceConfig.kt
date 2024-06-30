package com.dedicoder.common.library.config

interface SalesforceConfig {
    val clientId: String
    val clientSecret: String
    val username: String
    val password: String
    val securityToken: String
    val loginUrl: String
}