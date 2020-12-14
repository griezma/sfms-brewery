#!/bin/bash

docker run --name artemis -d --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis

echo "Execute \"docker stop artemis\" to stop and remove the container."

echo "Web-Console: http://localhost:8161"
echo "Login artemis/simetraehcapa"