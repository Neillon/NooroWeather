package com.neillon.weather.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neillon.weather.R
import com.neillon.weather.presentation.WeatherSearchState

@Composable
fun CityWeatherSearchBar(
    modifier: Modifier = Modifier,
    searchState: WeatherSearchState = WeatherSearchState.empty(),
    onSearchUpdate: (prev: String, curr: String) -> Unit,
    onSearch: (String) -> Unit
) {
    var text by rememberSaveable { mutableStateOf(searchState.query) }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 44.dp, start = 24.dp, end = 24.dp)
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            value = text,
            onValueChange = { newText ->
                onSearchUpdate(text, newText)
                text = newText
            },
            textStyle = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface),
            shape = RoundedCornerShape(16.dp),
            placeholder = {
                if (text.isEmpty()) {
                    Text(
                        stringResource(R.string.search_location),
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            },
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,

                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                disabledTextColor = MaterialTheme.colorScheme.onSurface,

                focusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(text)
                }
            )
        )
    }

}

@Preview
@Composable
private fun CitySearchBarPreview() {
    CityWeatherSearchBar(
        searchState = WeatherSearchState.empty(),
        onSearch = {},
        onSearchUpdate = { _, _ -> })
}