FROM kartoza/postgis:15
COPY *.sql /docker-entrypoint-initdb.d/