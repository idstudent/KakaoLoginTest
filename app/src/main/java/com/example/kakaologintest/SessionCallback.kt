package com.example.kakaologintest

import android.content.Intent
import android.util.Log
import com.kakao.auth.ErrorCode
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeResponseCallback
import com.kakao.usermgmt.response.model.UserProfile
import com.kakao.util.exception.KakaoException

class SessionCallback : ISessionCallback {

    override fun onSessionOpenFailed(exception: KakaoException?) {
        Log.e("tag", "fail")
    }

    override fun onSessionOpened() {
        requestMe()
    }
    fun requestMe() { //유저의 정보를 받아오는 함수
        UserManagement.requestMe(object : MeResponseCallback() {
            override fun onFailure(errorResult: ErrorResult) {
                Log.e("tag", "error message=$errorResult")
            }

            override fun onSessionClosed(errorResult: ErrorResult) {
                Log.e("tag", "onSessionClosed1 =$errorResult")
            }

            override fun onNotSignedUp() { //카카오톡 회원이 아닐시
                Log.e("tag", "onNotSignedUp ")
            }

            override fun onSuccess(result: UserProfile) {
                var nickname = result.nickname
                var profileImagePath = result.profileImagePath
                var thumbnailPath = result.thumbnailImagePath
                var id = result.id
                Log.i("tag", "nick $nickname")
                Log.i("tag", "profilepath $profileImagePath")
                Log.i("tag", "thumbnailpath $thumbnailPath")
                Log.i("tag", "id $id")
            }
        })
    }
}