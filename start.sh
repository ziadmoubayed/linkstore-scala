#!/bin/bash
nohup redis-server &
sbt run
