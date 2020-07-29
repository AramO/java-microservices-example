FROM mongo:4

# Add mongo user
#RUN groupadd -r mongo && \
#    useradd -r -g mongo mongo
#
#RUN set -e
#
#RUN echo "rs.initiate();" > /docker-entrypoint-initdb.d/replica-init.js
#CMD [ "--replSet", "MainRepSet" ]

WORKDIR /usr/src/configs

COPY ./mongo.conf .
CMD ["--config", "./mongo.conf"]


