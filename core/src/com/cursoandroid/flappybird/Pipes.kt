package com.cursoandroid.flappybird

import com.badlogic.gdx.graphics.Texture

class Pipes {
    var canoBaixo: Texture? = null
    var canoTopo: Texture? = null
    var posicaoMovimentoCanoHorizontal : Float = 0.toFloat()
    var espacoEntreCanos : Float = 0.toFloat()
    var alturaEntreCanosRandomica : Float = 0.toFloat()

    init {
        var config = Config()
        canoBaixo = Texture("cano_baixo_maior.png")
        canoTopo = Texture("cano_topo_maior.png")
        posicaoMovimentoCanoHorizontal= config.larguraDispositivo
        espacoEntreCanos = 200.toFloat()
    }
}