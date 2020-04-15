package com.example.kakaologintest

import android.app.Activity
import android.app.Application
import android.content.Context
import com.kakao.auth.*


class GlobalApplication : Application() {
    companion object{
        private var instance : GlobalApplication ?= null

        fun getGlobalApplicationContext() : GlobalApplication {
            if(instance == null) {
                throw IllegalStateException("exception")
            }
            return instance as GlobalApplication
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    class KakaoSDKAdapter : KakaoAdapter() {
        override fun getSessionConfig(): ISessionConfig {
            return object : ISessionConfig {
                override fun getAuthTypes(): Array<AuthType> {
                    return arrayOf(AuthType.KAKAO_ACCOUNT)
                }

                override fun isUsingWebviewTimer(): Boolean {
                    return false
                }

                override fun getApprovalType(): ApprovalType {
                    return ApprovalType.INDIVIDUAL
                }

                override fun isSaveFormData(): Boolean {
                    return true
                }
            }
        }

        override fun getApplicationConfig(): IApplicationConfig {
            return object : IApplicationConfig {
                override fun getTopActivity(): Activity {
                    return null!!
                }

                override fun getApplicationContext(): Context {
                    return GlobalApplication.getGlobalApplicationContext()
                }
            }
        }
    }
}