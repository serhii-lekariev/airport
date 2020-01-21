/*
 * Copyright 2020, TeamDev. All rights reserved.
 *
 * Redistribution and use in source and/or binary forms, with or without
 * modification, must retain the above copyright notice and the following
 * disclaimer.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package io.spine.example.airport.tl;

import io.spine.server.aggregate.Aggregate;
import io.spine.server.aggregate.Apply;
import io.spine.server.command.Assign;

class FlightAggregate extends Aggregate<FlightId, Flight, Flight.Builder> {

    @Assign
    FlightScheduled handle(ScheduleFlight command) {
        return FlightScheduled
                .newBuilder()
                .setId(command.getId())
                .setFrom(command.getFrom())
                .setTo(command.getTo())
                .setScheduledDeparture(command.getScheduledDeparture())
                .setScheduledArrival(command.getScheduledArrival())
                .vBuild();
    }

    @Assign
    FlightRescheduled handle(RescheduleFlight command) {
        return FlightRescheduled
                .newBuilder()
                .setId(command.getId())
                .setScheduledDeparture(command.getScheduledDeparture())
                .setScheduledArrival(command.getScheduledArrival())
                .vBuild();
    }

    @Apply
    private void on(FlightScheduled event) {
        builder().setFrom(event.getFrom())
                 .setTo(event.getTo())
                 .setScheduledDeparture(event.getScheduledDeparture())
                 .setScheduledArrival(event.getScheduledArrival());
    }

    @Apply
    private void on(FlightRescheduled event) {
        builder().setScheduledDeparture(event.getScheduledDeparture())
                 .setScheduledArrival(event.getScheduledArrival());
    }
}
