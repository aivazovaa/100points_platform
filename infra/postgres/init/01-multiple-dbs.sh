#!/bin/bash
set -e
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL
  CREATE DATABASE "100points_auth";
  CREATE DATABASE "100points_course";
  CREATE DATABASE "100points_enrollment";
EOSQL
