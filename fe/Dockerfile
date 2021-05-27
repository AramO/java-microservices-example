# FROM node:slim
#
# # Create app directory
# RUN mkdir -p /usr/src/app
# WORKDIR /usr/src/app
#
# # Install app dependencies
# COPY package.json /usr/src/app/
# RUN npm install
#
# # Bundle app source
# COPY . /usr/src/app
#
# RUN chown -Rh $user:$user /usr/src/app
# USER $user
#
# # EXPOSE 3000
# CMD [ "npm", "start" ]

# build environment
FROM node:slim as build
WORKDIR /app
ENV PATH /app/node_modules/.bin:$PATH
COPY package.json ./
COPY package-lock.json ./
RUN npm ci --silent
RUN npm install npm react-scripts -g --silent
COPY . ./
RUN npm run build

# production environment
FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
