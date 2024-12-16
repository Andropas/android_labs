import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lab3.data.ItemsData

@Composable
fun PlaceDetailsScreen(id: Int) {
    val itemState = remember {
        mutableStateOf(
            ItemsData.itemsList.first { it.id == id }
        )
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(id = itemState.value.image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Column(modifier = Modifier.fillMaxWidth().padding(10.dp)) {
            Text(
                text = itemState.value.name,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = itemState.value.description,
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp, top = 10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlaceDetailsScreenPreview() {
    PlaceDetailsScreen(id = 4)
}