package com.grauen.composetest.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ColumnScope.align
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import com.grauen.composetest.R

@Stable
fun String.toColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}

object ButtonAnimationDefinition {
    enum class AnimationState {
        START, MIDDLE, END
    }

    val leftButtonVisibilityKey = FloatPropKey()
    val rightButtonWidthKey = DpPropKey()
    val rightButtonBackgroundColor = ColorPropKey()

    val animationDefinition = transitionDefinition<AnimationState> {
        state(AnimationState.START) {
            this[leftButtonVisibilityKey] = 0F
            this[rightButtonWidthKey] = 56.dp
            this[rightButtonBackgroundColor] = Color.White

        }
        state(AnimationState.MIDDLE) {
            this[leftButtonVisibilityKey] = 1F
            this[rightButtonWidthKey] = 56.dp
            this[rightButtonBackgroundColor] = Color.White
        }
        state(AnimationState.END) {
            this[leftButtonVisibilityKey] = 1F
            this[rightButtonWidthKey] = 156.dp
            this[rightButtonBackgroundColor] = "#7543BF".toColor()
        }


    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ButtonsAnimation() {
    var animationState by remember { mutableStateOf(ButtonAnimationDefinition.AnimationState.START) }

    val animation = transition(
        definition = ButtonAnimationDefinition.animationDefinition,
        initState = ButtonAnimationDefinition.AnimationState.START,
        toState = animationState,
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .width(56.dp)
        ) {
            AnimatedVisibility(
                visible = animationState != ButtonAnimationDefinition.AnimationState.START,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                ButtonContainer(
                    modifier = Modifier
                        .height(56.dp)
                        .width(56.dp),
                    onClick = {
                        animationState = getBackState(animationState)
                    },
                    content = {
                        Image(asset = vectorResource(R.drawable.ic_arrow_left))
                    },
                )
            }
        }

        ButtonContainer(
            backgroundColor = animation[ButtonAnimationDefinition.rightButtonBackgroundColor],
            modifier = Modifier
                .height(56.dp)
                .width(
                    animation[ButtonAnimationDefinition.rightButtonWidthKey]
                ),
            onClick = {
                animationState = getNextState(animationState)
            },
            content = {
                if (animationState == ButtonAnimationDefinition.AnimationState.END) {
                    Row {
                        Text(text = "Repeat", color = Color.White)
                        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                        Image(asset = vectorResource(R.drawable.ic_repeat))
                    }
                } else {
                    Image(asset = vectorResource(R.drawable.ic_arrow_right))
                }
            },
        )
    }
}

@Composable
fun ButtonContainer(
    backgroundColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Box(
        children = content,
        gravity = ContentGravity.Center,
        modifier = modifier
            .border(1.dp, "#E4DFE9".toColor(), CircleShape)
            .background(animate(backgroundColor), CircleShape)
            .clip(CircleShape)
            .clickable(onClick = onClick)
    )
}

fun getBackState(currentState: ButtonAnimationDefinition.AnimationState): ButtonAnimationDefinition.AnimationState {
    return when (currentState) {
        ButtonAnimationDefinition.AnimationState.START -> {
            ButtonAnimationDefinition.AnimationState.START
        }
        ButtonAnimationDefinition.AnimationState.MIDDLE -> {
            ButtonAnimationDefinition.AnimationState.START
        }
        ButtonAnimationDefinition.AnimationState.END -> {
            ButtonAnimationDefinition.AnimationState.MIDDLE
        }
    }
}

fun getNextState(currentState: ButtonAnimationDefinition.AnimationState): ButtonAnimationDefinition.AnimationState {
    return when (currentState) {
        ButtonAnimationDefinition.AnimationState.START -> {
            ButtonAnimationDefinition.AnimationState.MIDDLE
        }
        ButtonAnimationDefinition.AnimationState.MIDDLE -> {
            ButtonAnimationDefinition.AnimationState.END
        }
        ButtonAnimationDefinition.AnimationState.END -> {
            ButtonAnimationDefinition.AnimationState.START
        }
    }
}


@Preview
@Composable
fun ButtonsAnimationPreview() {
    ButtonsAnimation()
}