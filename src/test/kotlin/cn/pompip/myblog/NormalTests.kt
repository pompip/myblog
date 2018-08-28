package cn.pompip.myblog

import org.junit.Test

class NormalTests{

    @Test
    fun testDrop(){
        val text = "<div></div>````"
        val line = text.dropWhile {
            " #*<>`/".contains(it)
        }

        println(line)
    }
}