package com.ingentity.movie.ui.home

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf

@Composable
fun HomePage() {

    val context = LocalContext.current
    var backgroundColor by remember {
        mutableStateOf(Color.Blue)
    }
    var text by remember {
        mutableStateOf("")
    }
    val speechData = mapOf<String, Pair<Color, String>>(
        "speak0" to (Color.Blue to "そんな不気味で薄暗い道を2つのライトがゆっくりと進んでいく。天魔「ここが音楽室だね」天魔は持っていたライトで音楽室の看板を照らした。リュウ「なあ、もう帰ろうぜ……？」怯えた声で天魔にすがるリュウ。"),
        "speak1" to (Color.Red to "辺りをライトで照らして安堵するリュウ。リュウ「はあ、じゃあ帰ろう。もう今日は終わりだ」")
    )
    LaunchedEffect(key1 = Unit, block = {
        var textToSpeech: TextToSpeech? = null
        textToSpeech = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(p0: String?) {
                        val data = speechData.get(p0)
                        if (data != null) {
                            backgroundColor = data.first
                            text = data.second
                        }
                    }

                    override fun onDone(p0: String?) {
                    }

                    override fun onError(p0: String?) {
                    }

                })
                speechData.forEach { key, data ->
                    textToSpeech?.speak(
                        data.second,
                        TextToSpeech.QUEUE_ADD,
                        bundleOf(),
                        key
                    )
                }
            }
        }

    })
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}