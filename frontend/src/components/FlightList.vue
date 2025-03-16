<template>
  <h1>Flights</h1>
  <div id="flightList">
    <div class="search-bar">
      <input
          type="text"
          v-model="filter"
          placeholder="Search by destination"
          @input="filterFlights"
      />
    </div>
    <div class="calendar">
      <input
          type="date"
          v-model="departureDateFilter"
          @input="filterFlights"
      />
    </div>
    <table>
      <thead>
      <tr>
        <th>Destination</th>
        <th>Departure Time</th>
        <th class="hover" @click="sortFlights('price')">
          Price
        </th>
        <th class="hover" @click="sortFlights('duration')">Flight Duration</th>
        <th></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="flight in filteredFlights" :key="flight.id">
        <td>{{ flight.destination }}</td>
        <td>{{ normalizeDepartureTime(flight.departureTime) }}</td>
        <td>{{ flight.price }}</td>
        <td>{{ flight.duration }}</td>
        <td>
          <button @click="bookFlight(flight.flight_id)">Book</button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();
    const flights = ref([]);
    const filter = ref('');
    const departureDateFilter = ref('');
    const filteredFlights = ref([]);
    const sortBy = ref('');
    const sortOrder = ref('asc');

    // Function to normalize the date format
    function normalizeDepartureTime(dateString) {
      const date = new Date(dateString);
      // Options for the date format
      const options = {
        day: '2-digit',
        month: '2-digit',
        year: 'numeric',
        hour: '2-digit',
        minute: '2-digit',
        hour12: false,
      };
      return date.toLocaleString('en-GB', options).replace(',', '');
    }

    // Function to filter flights based on destination and departure date
    const filterFlights = () => {
      filteredFlights.value = flights.value.filter(flight => {
        let matchesDestination = true;
        let matchesDepartureTime = true;
        if (filter.value) {
          matchesDestination = flight.destination.toLowerCase().includes(filter.value.toLowerCase());
        }
        // Check if the departure date filter is set and matches the flight's departure date
        if (departureDateFilter.value) {
          const flightDate = new Date(flight.departureTime).toISOString().split('T')[0];
          matchesDepartureTime = flightDate === departureDateFilter.value;
        }

        return matchesDestination && matchesDepartureTime;
      });
    };

    // Function to sort flights based on the specified column (price or duration)
    const sortFlights = (column) => {
      if (column === sortBy.value) {
        sortOrder.value = sortOrder.value === 'asc' ? 'desc' : 'asc';
      } else {
        sortBy.value = column;
        sortOrder.value = 'asc';
      }

      if (column === 'price') {
        filteredFlights.value.sort((a, b) => {
          if (a.price < b.price) return sortOrder.value === 'asc' ? -1 : 1;
          if (a.price > b.price) return sortOrder.value === 'asc' ? 1 : -1;
          return 0;
        });
      }
      if(column === 'duration') {
        filteredFlights.value.sort((a, b) => {
          if (a.duration < b.duration) return sortOrder.value === 'asc' ? -1 : 1;
          if (a.duration > b.duration) return sortOrder.value === 'asc' ? 1 : -1;
          return 0;
        });
      }
    };

    // Fetch the list of flights from the server when the component is mounted
    onMounted(() => {
      axios.get('http://localhost:8000/flights') // API endpoint to fetch flights
          .then(response => {
            flights.value = response.data;
            filteredFlights.value = response.data;
          })
          .catch(error => {
            console.log(error);
          });
    });

    // Function to handle flight booking, navigates to the seat selection page
    const bookFlight = (flightId) => {
      router.push(`/flights/${flightId}/seats`);
    };

    return {
      filteredFlights,
      bookFlight,
      filter,
      filterFlights,
      sortFlights,
      sortBy,
      sortOrder,
      normalizeDepartureTime,
      departureDateFilter,
    };
  }
};
</script>

<style scoped>
#flightList {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 30px;
}

.search-bar {
  margin-bottom: 20px;
}

input[type="text"] {
  padding: 10px;
  font-size: 16px;
  width: 100%;
}

table {
  width: 60%;
  border-collapse: collapse;
  background-color: antiquewhite;
}

th, td {
  border: 1px solid black;
  padding: 10px;
}

.hover {
  cursor: pointer;
}

button {
  padding: 10px 20px;
  font-size: 15px;
  background-color: blue;
  color: white;
  border: none;
  cursor: pointer;
  width: 100px;
  height: 50px;
}

button:hover {
  background-color: cornflowerblue;
}
input[type="date"] {
  padding: 10px;
  margin-bottom: 20px;
  font-size: 18px;
  width: 100%;
  height: 40px;
}
</style>