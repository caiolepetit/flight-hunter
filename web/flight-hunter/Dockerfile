FROM node:12.16-alpine AS build
WORKDIR /usr/src/app
COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build
FROM nginx:1.15.8-alpine
COPY --from=build /usr/src/app/dist/flight-hunter/ /usr/share/nginx/html