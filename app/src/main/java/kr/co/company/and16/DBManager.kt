package kr.co.company.and16

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBManager(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {


    override fun onCreate(db: SQLiteDatabase?) {
        // 이디야 테이블 생성
        db!!.execSQL("CREATE TABLE ediyaMenuDB (customMenuName text, customImage blob, existingMenuName text, price text, size text, espressoShotNumber text, tapiocaPearl text, hazelnutsSyrupNumber text, caramelSyrupNumber text, vanillaSyrupNumber text, irishSyrupNumber text, cafeSyrupNumber text, toppingSauce text, topping text)")

        // 공차테이블 생성
        db!!.execSQL("CREATE TABLE gongchaMenuDB (customMenuName text, customImage blob, existingMenuName text, price text, size text, espressoShotNumber text, sugar text, ice text, topping text)")

        // 스타벅스 테이블 생성
        db!!.execSQL("CREATE TABLE starbucksMenuDB (customMenuName text, customImage blob, existingMenuName text, price text, size text, espressoShotNumber text, vanillaSyrupNumber text, hazelnutsSyrupNumber text, caramelSyrupNumber text, lattebase text, base text, ice text, whipping text, drizzle text, roastNumber text)")


    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}