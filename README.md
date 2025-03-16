<H1>Documentation</H1>
<H2>Overview</H2>
<p>This is a full-stack application built using Vue.js for the frontend and Spring Boot for the backend. The app allows users to view flight details, book flights and select seats. It integrates with a backend API to retrieve flight information and store seat reservations.</p>

<h2>Installation</h2>
<p>1. Clone the repository</p><p></p>2. Install dependancies </p><code>npm install</code><p></p><p>3. Ensure that you have configured database username and password in .../resources/application.properties </p><p>4.Build and run the Spring Boot application</p><code>./mvnw spring-boot:run</code>

<h2>Frontend prerequisites</h2>
<ul>
  <li>Node.js: install Node.js</li>
  <li>Vue.js: The project is built with Vue 3, which requires Vue CLI. You can install the CLI globally with npm if it's not already installed:</li> <code>npm install -g @vue/cli</code>
</ul>

<h3>Running the frontend</h3>
<p>After installing the dependencies, make sure you are in the frontend directory, run the following command to start the development server</p> <code>npm run serve</code><p></p><p>Open a browser and visit http://localhost:8080 to see the app running.</p>
<h3>Vue components</h3>
<ul>
  <li>Flight List Component (FlightList.vue): Displays a list of available flights. Users can filter by destination,departure date, etc.</li>
  <li>Seat Selection Component (SeatSelection.vue): Allows users to select seats for a flight. Includes filters for seat type (window, exit, more legroom).</li>
</ul>
<p>The frontend interacts with the backend through Axios for API requests.</p>

<h2>Backend prerequisites</h2>
<ul>
  <li>Java: JDK 21</li>
  <li>Maven: Use maven to build and run the application</li>
  <li>PostgreSQL</li>
</ul>
<h3>Running the backend</h3>
<p>To run the backend locally, execute: <code>./mvnw spring-boot:run</code></p><p>The backend will run on <code>http://localhost:8000</code></p><p>Ensure that you have configured database username and password in .../resources/application.properties</p>
<h3>Endpoints</h3>
<ul>
  <li> GET <code>/flights</code>: Retrieves a list of all available flights.</li>
  <li> GET <code>/flights/{id}</code>:Retrieves a specific flight by its ID.</li>
  <li> POST <code>/flights</code>: Adds a new flight to the system.</li>
  <li> GET <code>/flights/{id}/seats</code>: Retrieves seat information for a specific flight.</li>
  <li> PUT <code>/seats/reserve</code>: Confirms seat reservations for selected seats</li>
</ul>

<h2>Additional notes</h2>
<p>CORS Handling: If you're accessing the backend from a different port, you may need to configure CORS on the Spring Boot backend.</p>
