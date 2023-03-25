package com.ingentity.movie.ui.home

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import com.ingentity.movie.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun VideoComposable(
    isShown: Boolean,
    speechData: List<Pair<Int, String>>
) {
    val context = LocalContext.current
    var backgroundImage by remember {
        mutableStateOf(speechData.getOrNull(0)?.first ?: R.drawable.tp_1)
    }
    var text by remember {
        mutableStateOf("")
    }

    var textToSpeech: TextToSpeech? by remember { mutableStateOf(null) }
    var job: Job? = null
    LaunchedEffect(key1 = isShown, block = {
        textToSpeech?.stop()
        job?.cancel()
        job = launch {
            if (isShown) {
                textToSpeech = TextToSpeech(context) {
                    if (it == TextToSpeech.SUCCESS) {
                        textToSpeech?.setOnUtteranceProgressListener(object :
                            UtteranceProgressListener() {
                            override fun onStart(p0: String?) {
                                val key = p0?.toIntOrNull()
                                if (key != null) {
                                    backgroundImage = speechData[key].first
                                    text = speechData[key].second
                                }
                            }

                            override fun onDone(p0: String?) {
                            }

                            override fun onError(p0: String?) {
                            }

                        })
                        speechData.forEachIndexed { index, data ->
                            textToSpeech?.speak(
                                data.second,
                                TextToSpeech.QUEUE_ADD,
                                bundleOf(),
                                index.toString()
                            )
                        }
                    }
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