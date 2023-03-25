package com.ingentity.movie.ui.home

import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePage() {
    val pagerState = rememberPagerState()
    val speechData = listOf(
        (R.drawable.tp_1 to "そんな不気味で薄暗い道を2つのライトがゆっくりと進んでいく。"),
        (R.drawable.tp_1 to "辺りをライトで照らして安堵するリュウ。リュウ「はあ、じゃあ帰ろう。もう今日は終わりだ」"),
        (R.drawable.tp_1 to "天魔「ここが音楽室だね」"),
        (R.drawable.tp_2 to "天魔は持っていたライトで音楽室の看板を照らした。"),
        (R.drawable.tp_2 to "リュウ「なあ、もう帰ろうぜ……？」怯えた声で天魔にすがるリュウ。")
    )

    val speechData2 = listOf(
        (R.drawable.tp_2 to "ゆっくりと進んでいく。"),
        (R.drawable.tp_1 to "辺りるリュウ。リュウ「はあ、じゃあ帰ろう。もう今日は終わりだ」"),
        (R.drawable.tp_1 to "天魔「ここが音楽室だね」"),
        (R.drawable.tp_2 to "天魔は持っていたライトで音楽室の看板を照らした。"),
        (R.drawable.tp_2 to "リュウ「なあ、もう帰ろうぜ……？」怯えた声で天魔にすがるリュウ。")
    )
    VerticalPager(modifier = Modifier.fillMaxSize(), pageCount = 5, state = pagerState) { page ->
        VideoComposable(
            pagerState.currentPage == page,
            if (page % 2 == 0) speechData else speechData2
        )
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    HomePage()
}