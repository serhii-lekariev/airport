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

package io.spine.example.airport.tl.passengers;

import static io.spine.example.airport.tl.passengers.BoardingStatus.BOARDED;
import static io.spine.example.airport.tl.passengers.BoardingStatus.NOT_BOARDED;
import static io.spine.example.airport.tl.passengers.BoardingStatus.WILL_NOT_BE_BOARDED;

/**
 * An interface for the {@code TsaPassenger}.
 */
public interface TsaPassengerMixin extends TsaPassengerOrBuilder {

    /**
     * Obtains the {@link BoardingStatus} of the passenger based on their
     * {@linkplain #getStatus() security status}.
     */
    default BoardingStatus boardingStatus() {
        BoardingStatus status;
        switch (this.getStatus()) {
            case PASSED:
                status = BOARDED;
                break;
            case DENIED: // Fallthrough intended.
            case DETAINED:
                status = WILL_NOT_BE_BOARDED;
                break;
            case NOT_ATTEMPTED:
            case UNRECOGNIZED:
            case TPS_UNKNOWN:
            default:
                status = NOT_BOARDED;
        }
        return status;
    }
}
