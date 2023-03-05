package com.jjsan.scratchticket.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jjsan.scratchticket.R
import com.jjsan.scratchticket.enums.TicketStatusEnum
import java.util.UUID

@Composable
fun TicketStatus(
    ticketStatus: TicketStatusEnum,
    ticketCode: UUID?
) {
    Column() {
        Text(
            stringResource(R.string.ticket_status),
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 10.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

        val backGroundColor = getBackGroundColorByActivationStatus(ticketStatus)

        Box(Modifier.background(backGroundColor)) {
            Text(
                getTicketStatus(ticketStatus = ticketStatus),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(15.dp)
            )
        }

        ticketCode?.run {
            Column() {
                Text(
                    text = stringResource(id = R.string.ticket_code),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(top = 25.dp)
                )
                Text(
                    text = ticketCode.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

private fun getBackGroundColorByActivationStatus(
    ticketStatus: TicketStatusEnum
): Color {
    return when (ticketStatus) {
        TicketStatusEnum.NOT_SCRATCHED -> Color.LightGray
        TicketStatusEnum.SCRATCHED_NOT_ACTIVATED -> Color.Cyan
        TicketStatusEnum.SCRATCHED_ACTIVATED -> Color.Green
    }
}

@Composable
internal fun getTicketStatus(ticketStatus: TicketStatusEnum): String {
    return when (ticketStatus) {
        TicketStatusEnum.NOT_SCRATCHED -> stringResource(id = R.string.not_scratched)
        TicketStatusEnum.SCRATCHED_NOT_ACTIVATED -> stringResource(id = R.string.scratched_not_activated)
        TicketStatusEnum.SCRATCHED_ACTIVATED -> stringResource(id = R.string.scratched_activated)
    }
}
