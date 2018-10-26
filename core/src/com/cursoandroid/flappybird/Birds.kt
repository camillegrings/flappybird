package com.cursoandroid.flappybird

import com.badlogic.gdx.graphics.Texture

class Birds {
    var passaros: Array<Texture?> = arrayOfNulls(5)
    var velocidadeQueda : Float = 0.toFloat()
    var posicaoInicialVertical : Float = 0.toFloat()


    init{
        var config = Config()
        passaros[0] = Texture("passaro1.png")
        passaros[1] = Texture("passaro2.png")
        passaros[2] = Texture("passaro3.png")
        posicaoInicialVertical = config.alturaDispositivo / 2
    }

}