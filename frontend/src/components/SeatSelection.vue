<template>
  <div class="seat-selection">
    <h2>Choose Your Seat for Flight {{ flightId }}</h2>

    <div>
      <label>
        <input type="checkbox" v-model="filterWindowSeats" />
        Highlight window seats
      </label>
      <label>
        <input type="checkbox" v-model="filterLegRoom" />
        Highlight more legroom seats
      </label>
      <label>
        <input type="checkbox" v-model="filterExit" />
        Highlight exit rows
      </label>
    </div>

    <table>
      <tr v-for="row in seatRows" :key="row">
        <td v-for="seat in seats[row]" :key="seat.seat_id">
          <button
              :class="{
              taken: seat.taken,
              available: !seat.taken,
              selected: selectedSeats.some(s => s.seat_id === seat.seat_id),
              'highlight-window-seat': filterWindowSeats && seat.window && !seat.taken,
              'highlight-leg-room': filterLegRoom && seat.moreLegroom && !seat.taken,
              'highlight-exit-row': filterExit && seat.closeToExit && !seat.taken,
            }"
              :disabled="seat.taken"
              @click="toggleSeatSelection(seat)"
          >
            {{ seat.rowNum }}{{ seat.seatChar }}
          </button>
        </td>
      </tr>
    </table>
    <button id="confirm" @click="confirmReservation">Confirm Reservation</button>
    <button id="back" @click="goBack">Back to Flights</button>
  </div>
</template>
<script>
import axios from 'axios';
import { useRoute, useRouter } from 'vue-router';
import { ref, onMounted } from 'vue';

export default {
  setup() {
    const route = useRoute();
    const router = useRouter();
    const flightId = route.params.id;
    const seats = ref({});
    const seatRows = ref([]);
    const filterWindowSeats = ref(false);
    const filterLegRoom = ref(false);
    const filterExit = ref(false);
    const selectedSeats = ref([]);

    // Fetch seats for the flight
    onMounted(() => {
      axios.get(`http://localhost:8000/flights/${flightId}/seats`)
          .then(response => {
            const groupedSeats = {};
            // Sort seats by row number and then by seat character to display them in correct order
            response.data.sort((a, b) => {
              if (a.rowNum !== b.rowNum) return a.rowNum - b.rowNum;
              return a.seatChar.localeCompare(b.seatChar);
            });
            response.data.forEach(seat => {
              if (!groupedSeats[seat.rowNum]) {
                groupedSeats[seat.rowNum] = [];
              }
              groupedSeats[seat.rowNum].push(seat);
            });
            seats.value = groupedSeats;
            seatRows.value = Object.keys(groupedSeats);
          })
          .catch(error => console.log(error));
    });


    // Function to toggle seat selection
    const toggleSeatSelection = (seat) => {
      if (seat.taken) return;

      const index = selectedSeats.value.findIndex(s => s.seat_id === seat.seat_id);

      if (index !== -1) {
        selectedSeats.value.splice(index, 1);
      } else {
        // Limit the number of selected seats to 3
        if (selectedSeats.value.length >= 3) {
          alert("You can only select up to 3 seats.");
          return;
        }
        selectedSeats.value.push(seat);
      }
    };

    const confirmReservation = () => {
      if (selectedSeats.value.length === 0) {
        alert("Please select at least one seat.");
        return;
      }

      // Send a request to reserve the selected seats
      const seatIds = selectedSeats.value.map(seat => seat.seat_id);
      axios.put(`http://localhost:8000/seats/reserve`, { seatIds })
          .then(() => {
            alert("Seats reserved successfully!");
            selectedSeats.value.forEach(seat => seat.taken = true);
            selectedSeats.value = [];
          })
          .catch(error => alert("Error reserving seats: " + error));
    };

    const goBack = () => {
      router.push("/");
    };

    return {
      flightId, seats, seatRows,
      filterWindowSeats, filterLegRoom, filterExit,
      selectedSeats, toggleSeatSelection, confirmReservation, goBack
    };
  }
};
</script>

<style scoped>
.seat-selection {
  text-align: center;
  padding: 20px;
}

table {
  margin: auto;
  border-collapse: separate;
  border-spacing: 10px;
}

button {
  width: 50px;
  height: 50px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
#back{
  width: 100px;
  height: 50px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.available {
  background-color: green;
  color: white;
}

.taken {
  background-color: red;
  color: white;
  cursor: not-allowed;
}

.highlight-window-seat {
  background-color: lightblue;
  color: black;
}

.highlight-leg-room {
  background-color: lightblue;
  color: black;
}
.highlight-exit-row {
  background-color: lightblue;
  color: black;
}
.selected {
  background-color: yellow;
  color: black;
  border: 2px solid black;
}

#confirm {
  width: 100px;
  height: 50px;
  font-size: 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-right: 50px;
  background-color: darkgreen;
}

</style>