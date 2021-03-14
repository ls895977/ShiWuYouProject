package com.example.shiwuyouproject.ui.bean

/**
 * "auth_id": 1,实名认证 <number>
"auth_job": 0,工作性质 <number>
"auth_working": 0,工作状态 <number>
"auth_subject": 1,科目认证 <number>
"auth_course_time": 1,授课时间 <number>
"auth_course_area": 1,授课地址 <number>
"auth_service": 0,服务准则 <number>
"auth_media": 1,音视频认证 <number>
"auth_step": 1认证步骤（1，2，3，4） <number>
 */
data class UserAuthBean(
    val auth_course_area: Int,
    val auth_course_time: Int,
    val auth_id: Int,
    val auth_info: Int,
    val auth_job: Int,
    val auth_media: Int,
    val auth_service: Int,
    val auth_step: Int,
    val auth_subject: Int,
    val auth_working: Int
)