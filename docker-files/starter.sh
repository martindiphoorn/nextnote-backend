#!/bin/bash

set -e

cmd="$@"

# Do an initial wait
sleep 5

# Wait for the server to become available
until mysql -h "${dbHost}" -P "${dbPort}" -u"${dbUsername}" -p"${dbPassword}" -e 'show processlist'; do
  >&2 echo "Database is unavailable - sleeping"
  sleep 5
done

>&2 echo "Database is up - executing command"
exec $cmd