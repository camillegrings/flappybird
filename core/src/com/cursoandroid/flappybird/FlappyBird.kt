package com.cursoandroid.flappybird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.math.Rectangle
import java.util.*

class FlappyBird : ApplicationAdapter() {
    private var batch: SpriteBatch? = null
    private var numeroRandomico: Random? = null
    private var estadoJogo: GameState = GameState.AGUARDANDO
    private var pontuacao: Int = 0
    private var variacao: Double = 0.toDouble()
    private var deltaTime: Float = 0.toFloat()
    private var marcouPonto: Boolean = false

    private var passaros: Birds? = null
    private var game: Game? = null
    private var canos: Pipes? = null
    private var config: Config? = null

    override fun create() {
        batch = SpriteBatch()
        numeroRandomico = Random()
        passaros = Birds()
        game = Game()
        canos = Pipes()
        config = Config()
    }

    override fun render() {
        config?.firstConfiguration()

        deltaTime = Gdx.graphics.deltaTime
        variacao += deltaTime * 10

        if (variacao > 2) variacao = 0.toDouble()

        if (estadoJogo == GameState.AGUARDANDO) {
            if (Gdx.input.justTouched()) estadoJogo = GameState.INICIADO
        } else if (estadoJogo == GameState.INICIADO) {
            passaros!!.velocidadeQueda++
            if (passaros!!.posicaoInicialVertical > 0 || passaros!!.velocidadeQueda < 0) passaros!!.posicaoInicialVertical -= passaros!!.velocidadeQueda

            canos!!.posicaoMovimentoCanoHorizontal -= deltaTime * 200

            if (Gdx.input.justTouched()) passaros!!.velocidadeQueda = (-15).toFloat()

            if (canos!!.posicaoMovimentoCanoHorizontal < -canos!!.canoTopo!!.width) {
                canos!!.posicaoMovimentoCanoHorizontal = config!!.larguraDispositivo
                canos!!.alturaEntreCanosRandomica = numeroRandomico!!.nextInt(400).toFloat() - 200
                marcouPonto = false
            }

            if (canos!!.posicaoMovimentoCanoHorizontal + canos!!.canoTopo!!.width < 120) {
                if (!marcouPonto) {
                    pontuacao++
                    marcouPonto = true
                }
            }
        } else {
            passaros!!.velocidadeQueda++
            if (passaros!!.posicaoInicialVertical > 0 || passaros!!.velocidadeQueda < 0) passaros!!.posicaoInicialVertical -= passaros!!.velocidadeQueda
            variacao = 0.toDouble()
            if (Gdx.input.justTouched()) {
                estadoJogo = GameState.AGUARDANDO
                pontuacao = 0
                passaros!!.velocidadeQueda = 0.toFloat()
                passaros!!.posicaoInicialVertical = config!!.alturaDispositivo / 2
                canos!!.posicaoMovimentoCanoHorizontal = config!!.larguraDispositivo
                marcouPonto = false
            }
        }
        batch?.projectionMatrix = config?.camera!!.combined

        batch?.begin()

        batch?.draw(game!!.fundo!!, 0.toFloat(), 0.toFloat(), config!!.larguraDispositivo, config!!.alturaDispositivo)

        batch?.draw(canos!!.canoTopo,
                canos!!.posicaoMovimentoCanoHorizontal,
                config!!.alturaDispositivo / 2 + canos!!.espacoEntreCanos / 2 + canos!!.alturaEntreCanosRandomica,
                canos!!.canoTopo!!.width.toFloat(),
                canos!!.canoTopo!!.height.toFloat())

        batch?.draw(canos!!.canoBaixo, canos!!.posicaoMovimentoCanoHorizontal,
                config!!.alturaDispositivo / 2 - canos!!.canoBaixo!!.height - canos!!.espacoEntreCanos / 2 + canos!!.alturaEntreCanosRandomica,
                canos!!.canoBaixo!!.width.toFloat(), canos!!.canoBaixo!!.height.toFloat())

        batch?.draw(passaros!!.passaros[variacao.toInt()], 120.toFloat(),
                passaros!!.posicaoInicialVertical, passaros!!.passaros[variacao.toInt()]!!.width.toFloat(), passaros!!.passaros[variacao.toInt()]!!.height.toFloat())

        game!!.pontos?.draw(batch, pontuacao.toString(), config!!.larguraDispositivo / 2, config!!.alturaDispositivo - 100)

        if (estadoJogo == GameState.FINALIZADO) {
            batch?.draw(game!!.gameOver, config!!.larguraDispositivo / 2 - ((game!!.gameOver!!.width + 200) / 2),
                    config!!.alturaDispositivo / 2, (game!!.gameOver!!.width + 200).toFloat(), game!!.gameOver!!.height.toFloat())

            game!!.mensagem?.draw(batch, "Toque para Reiniciar", config!!.larguraDispositivo / 2 - 200, config!!.alturaDispositivo / 2 - game!!.gameOver!!.height / 2)
        }

        batch?.end()

        game!!.passaroCirculo?.set((130 + passaros!!.passaros[0]!!.width / 2).toFloat(), passaros!!.posicaoInicialVertical + passaros!!.passaros[0]!!.width / 2, (passaros!!.passaros[0]!!.width / 2).toFloat())

        game!!.canoBaixoRetangulo = Rectangle(
                canos!!.posicaoMovimentoCanoHorizontal, config!!.alturaDispositivo / 2 - canos!!.canoBaixo!!.height - canos!!.espacoEntreCanos / 2 + canos!!.alturaEntreCanosRandomica,
                canos!!.canoBaixo!!.width.toFloat(), canos!!.canoBaixo!!.height.toFloat()
        )

        game!!.canoTopoRetangulo = Rectangle(
                canos!!.posicaoMovimentoCanoHorizontal, config!!.alturaDispositivo / 2 + canos!!.espacoEntreCanos / 2 + canos!!.alturaEntreCanosRandomica,
                canos!!.canoTopo!!.width.toFloat(), canos!!.canoTopo!!.height.toFloat()
        )

        if (Intersector.overlaps(game!!.passaroCirculo, game!!.canoBaixoRetangulo) || Intersector.overlaps(game!!.passaroCirculo, game!!.canoTopoRetangulo)
                || passaros!!.posicaoInicialVertical <= 0 || passaros!!.posicaoInicialVertical >= config!!.alturaDispositivo) {
            estadoJogo = GameState.FINALIZADO;
        }

    }

    override fun resize(width: Int, height: Int) {
        config?.viewport?.update(width, height)
    }

}