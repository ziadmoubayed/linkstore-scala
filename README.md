# linkstore-scala
[![CircleCI](https://circleci.com/gh/ziadmoubayed/linkstore-scala.svg?style=svg)](https://circleci.com/gh/ziadmoubayed/linkstore-scala)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ziadmoubayed_linkstore-scala&metric=alert_status)](https://sonarcloud.io/dashboard?id=ziadmoubayed_linkstore-scala)

Link Store is a system for storing URLs with social interactions and exporting statistical summaries associated with these URLs. It is a scala application packaged using sbt.
Link Store currently uses either [Redis Hashes](https://redis.io/topics/data-types) or an In-Memory Set to store the data. You can add different data stores by extending the [LinkVORepository](https://github.com/ziadmoubayed/linkstore-scala/blob/master/src/main/scala/com/github/newswhip/linkstore/repo/LinkVORepository.scala). When the project starts it presents a REPL which can be used to manage the system.
The REPL exposes these functionalites.
  - Add a new url to the store with a social score.
  - Remove a url from the store.
  - Export a report that aggregates sum of social scores and domain count.
  - Help & Manual

## Installation

Link Store uses the InMemory Store by Default. To switch to redis you can add the environment variable LIVE_STORE_ENV="redis".
### Docker
Link Store is very easy to install and deploy in a Docker container.

```sh
$ git clone https://github.com/ziadmoubayed/linkstore-scala.git
$ cd linkstore-scala
$ docker build -t linkstorescala .
```
This will create the Link Store image and pull in the necessary dependencies.

Once done, run the Docker image. To use redis add the environment variable -e LIVE_STORE_ENV='redis'. To persist the redis data you need to map an external dir to the redis internal data directory.
Note: You need to start the docker in interactive mode to use the REPL.

```sh
$ docker run -it -e LIVE_STORE_ENV='redis' -v /usr/lib/redis:/usr/lib/redis linkstorescala
```

### Building for source
Choose and download a [release](https://github.com/ziadmoubayed/linkstore-scala/releases) from github. Make sure to have sbt installed. To use redis you need to export the above environment variable and you should have redis installed and available on port 6379.

```sh
$ sbt run
```

## Usage
When the project starts, you will be presented with a REPL. You can use the following commands to access the REPL.

```
add http://google.com 12312
add facebook.com/123123 123
export
remove http://google.com
add twitter/tweets/latest 1234124
export -f /tmp/export.csv
````
Note: Export prints to the console unless -f or --file is specified.

### Todos

  - Use Event Sourcing to store ADD/DELETE events instead of doing CRUD operations.
 - Use kafka as the main source of truth.
 - Write MORE Tests.
 - Test redis persistance.

License
----

MIT