import { createRouter, createWebHistory } from 'vue-router';
import FlightList from "@/components/FlightList.vue";
import SeatSelection from "@/components/SeatSelection.vue";
import seatSelection from "@/components/SeatSelection.vue";

const routes = [
    { path: '/', component: FlightList },
    { path: '/flights/:id/seats', name: seatSelection, component: SeatSelection, props: true }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;