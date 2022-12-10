package info.degirona.creativelab.ui.experiments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import info.degirona.creativelab.R
import info.degirona.creativelab.model.ExperimentModel
import info.degirona.creativelab.model.ExperimentModel.ExperimentTypeModel
import info.degirona.creativelab.model.ExperimentType
import info.degirona.creativelab.ui.experiments.typography.SimpleStroke
import info.degirona.creativelab.ui.experiments.typography.StrokeAndAnimationV1
import info.degirona.creativelab.ui.experiments.typography.StrokeAndAnimationV2
import info.degirona.creativelab.ui.experiments.typography.StrokedReveal

@Composable
fun Experiments(
    experiments: List<ExperimentModel>,
    onClick: (ExperimentModel) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp),
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp)
    ) {
        item {
            Spacer(modifier = Modifier.statusBarsPadding())
        }
        items(experiments) {
            ExperimentItem(
                experiment = it,
                onClick = onClick,
            )
        }
        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }
    }
}

@Composable
fun ExperimentItem(
    experiment: ExperimentModel,
    onClick: (ExperimentModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxWidth()
            .requiredHeightIn(min = 80.dp)
    ) {
        Box(
            modifier = Modifier
                .clickable { onClick(experiment) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(
                    text = experiment.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = experiment.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 8.dp, end = 48.dp)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.chemical_beaker),
                contentDescription = "Chemical beaker",
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.BottomCenter,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .requiredWidth(72.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 8.dp, y = 24.dp)
                    .alpha(0.3f)
            )
        }
    }
}

@Composable
fun ExperimentHolder(
    typeModel: ExperimentTypeModel,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = 32.dp)
            .statusBarsPadding()
    ) {
        typeModel.Composable(
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Composable
fun ExperimentTypeModel.Composable(modifier: Modifier) {
    when (this.type) {
        is ExperimentType.Typography.SimpleStroke -> SimpleStroke(modifier)
        is ExperimentType.Typography.StrokeAndAnimationV1 -> StrokeAndAnimationV1(modifier)
        is ExperimentType.Typography.StrokeAndAnimationV2 -> StrokeAndAnimationV2(modifier)
        is ExperimentType.Typography.StrokedReveal -> StrokedReveal(modifier)
    }
}