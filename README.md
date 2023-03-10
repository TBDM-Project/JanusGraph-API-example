<!--
*** Thanks for checking out the Best-README-Template. If you have a suggestion
*** that would make this better, please fork the repo and create a pull request
*** or simply open an issue with the tag "enhancement".
*** Thanks again! Now go create something AMAZING! :D
-->

  <h3 align="center">JanusGraph API example</h3>


<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
Simple REST API implementation to query an instance of JanusGraph grpah database, using Cassandra as backend storage database. A the result for the project of Technologies for Big Data Mangement exam in the Computer Science degree of University of Camerino.


### Built With

This section should list any major frameworks that you built your project using. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.
* [SpringBoot](https://getbootstrap.com)
* [JanusGraph](https://janusgraph.org/)
* [Apache Cassandra](https://cassandra.apache.org/_/index.html)



<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
* Docker Installation
  
  See  [Docker get started](https://www.docker.com/get-started/ ).
 
* Download Cassandra image
  ```sh
  docker pull cassandra
  ```
* JanusGraph local installation

  Download JanusGraph distribution from [JanusGraph website](https://github.com/JanusGraph/janusgraph/releases) and unzip it.

### Installation

1. Start Cassandra container
   ```sh
   docker run -d -p 7001:7001 -p 7199:7199 -p 9042:9042 -p 9160:9160 \      
   -v  /var/lib/cassandra:/var/lib/cassandra \  
   -e CASSANDRA_START_RPC=true \  
   --name cass  cassandra
   ```
2. Inside the JanusGraph directory, start the JanusGraph-server
   ```sh
   ./bin/janusgraph-server.sh start
   ```
3. Clone the repo
   ```sh
   git clone https://github.com/damiano00/ApiAssetJanus.git
   ```
4. Install Maven dependencies
   ```sh
   mvn install
   ```
5. Run the project
   ```sh
   mvn spring-boot:run
   ```



<!-- USAGE EXAMPLES -->
## Usage

Import the Postman collection present in the project and run requests.

[![Product Name Screen Shot][product-screenshot]](https://example.com)

<!-- CONTACT -->
## Contact

Niccolò Francioni  - niccolo.francioni@studenti.unicam.it

Luca Mozzoni  - luca.mozzoni@studenti.unicam.it

Damiano Pasquini  - damiano.pasquini@studenti.unicam.it

Project Link: [https://github.com/damiano00/ApiAssetJanus](https://github.com/damiano00/ApiAssetJanus)



<!-- ACKNOWLEDGEMENTS -->
## Acknowledgements
* [Apache TinkerPop](https://tinkerpop.apache.org/)
* [Pactical Gremlin Book](https://kelvinlawrence.net/book/Gremlin-Graph-Guide.html)
* [JanusGraph](https://janusgraph.org/)






