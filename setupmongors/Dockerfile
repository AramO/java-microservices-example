FROM mongo

COPY mongoSetup.js .
COPY setup.sh .
COPY dbSetup.js .

RUN ["chmod", "+x", "./setup.sh"]
CMD ["./setup.sh"]


