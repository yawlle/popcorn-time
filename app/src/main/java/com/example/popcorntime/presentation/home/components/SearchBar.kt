package com.example.popcorntime.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.popcorntime.R

@Composable
fun SearchBar(
    modifier : Modifier = Modifier,
    value: String,
    hint: String,
    onValueChange: (String) -> Unit,
    isFocused: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit = {}
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(7.dp)
            )
            .clip(shape = RoundedCornerShape(7.dp))
            .focusRequester(focusRequester)
            .clickable {
                onClick()
            },
        value = value,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        textStyle = MaterialTheme.typography.labelSmall,
        enabled = enabled,
        maxLines = 1,
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
        },
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = "",
                    tint = colorResource(id = R.color.search_color)
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp)
                ) {
                    if (value.isEmpty()) {
                        Text(text = hint, style = MaterialTheme.typography.labelSmall, color =  colorResource(id = R.color.hint_color))
                    }
                    innerTextField()
                }
                if (value.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "",
                        tint = colorResource(id = R.color.search_color),
                        modifier = Modifier.clickable {
                            onValueChange("")
                            focusManager.clearFocus()
                        }
                    )
                }
            }
        }
    )

    LaunchedEffect(key1 = Unit) {
        if (isFocused) {
            focusRequester.requestFocus()
        }
    }
}

@Preview
@Composable
private fun SearchBarPreview() {
    SearchBar(modifier = Modifier, value = "", hint = "Hint", onValueChange = {})
}