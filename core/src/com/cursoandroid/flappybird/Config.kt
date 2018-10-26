package com.cursoandroid.flappybird

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.badlogic.gdx.utils.viewport.Viewport

class Config {
    var larguraDispositivo : Float = 0.toFloat()
    var alturaDispositivo : Float = 0.toFloat()
    var camera : OrthographicCamera? = null
    var viewport : Viewport? = null

    companion object {
        var VIRTUAL_WIDTH: Float = 768.toFloat()
        var VIRTUAL_HEIGTH: Float = 1024.toFloat()
    }

    init {
        larguraDispositivo = VIRTUAL_WIDTH
        alturaDispositivo = VIRTUAL_HEIGTH
        camera = OrthographicCamera()
        camera?.position?.set(VIRTUAL_WIDTH/2, VIRTUAL_HEIGTH/2, 0.toFloat())
        viewport = StretchViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGTH, camera)
        larguraDispositivo = VIRTUAL_WIDTH
        alturaDispositivo = VIRTUAL_HEIGTH
    }

    fun firstConfiguration(){
        camera?.update()

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
    }
}