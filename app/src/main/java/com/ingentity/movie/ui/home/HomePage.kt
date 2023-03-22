package com.ingentity.movie.ui.home

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.ingentity.movie.R

@Composable
fun HomePage() {

    val context = LocalContext.current
    var backgroundImage by remember {
        mutableStateOf(R.drawable.tp_1)
    }
    var text by remember {
        mutableStateOf("")
    }
    val speechData = mapOf(
        "speak0" to (R.drawable.tp_1 to "そんな不気味で薄暗い道を2つのライトがゆっくりと進んでいく。"),
        "speak1" to (R.drawable.tp_1 to "辺りをライトで照らして安堵するリュウ。リュウ「はあ、じゃあ帰ろう。もう今日は終わりだ」"),
        "speak2" to (R.drawable.tp_1 to "天魔「ここが音楽室だね」"),
        "speak3" to (R.drawable.tp_2 to "天魔は持っていたライトで音楽室の看板を照らした。"),
        "speak4" to (R.drawable.tp_2 to "リュウ「なあ、もう帰ろうぜ……？」怯えた声で天魔にすがるリュウ。")
    )
    LaunchedEffect(key1 = Unit, block = {
        var textToSpeech: TextToSpeech? = null
        textToSpeech = TextToSpeech(context) {
            if (it == TextToSpeech.SUCCESS) {
                textToSpeech?.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                    override fun onStart(p0: String?) {
                        val data = speechData.get(p0)
                        if (data != null) {
                            backgroundImage = data.first
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
    ) {
        Image(
            painter = painterResource(id = backgroundImage),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 32.dp)
                .background(Color(0x00, 0x00, 0x00, 0xA0))
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(8.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}