FROM clojure:openjdk-14-lein-2.9.1-alpine
RUN apk add npm
RUN npm install -g http-server

WORKDIR /opt

RUN mkdir -p www/pretty-print.net
COPY ./resources/public/ www/pretty-print.net/

EXPOSE 8080/tcp
WORKDIR /opt/www/pretty-print.net
RUN mv index.release.html index.html
ENTRYPOINT ["http-server"]
