package com.cursoandroid.flappybird

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Rectangle

class Game {
    var fundo: Texture? = null
    var gameOver : Texture? = null
    var pontos : BitmapFont? = null
    var mensagem : BitmapFont? = null

    var passaroCirculo : Circle? = null
    var canoBaixoRetangulo : Rectangle? = null
    var canoTopoRetangulo : Rectangle? = null

    init {
        fundo = Texture("fundo.png")
        gameOver = Texture("game_over.png")
        pontos = BitmapFont()
        pontos?.setColor(Color.WHITE)
        pontos?.data?.scale(5.toFloat())
        mensagem = BitmapFont()
        mensagem?.setColor(Color.WHITE)
        mensagem?.data?.scale(2.toFloat())
        passaroCirculo = Circle()
        canoBaixoRetangulo = Rectangle()
        canoTopoRetangulo = Rectangle()
    }
}