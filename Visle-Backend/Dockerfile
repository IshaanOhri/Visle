
FROM node:14.8.0-alpine3.12

WORKDIR /app

COPY package*.json ./

RUN npm i --only=production --no-optional

COPY . .

EXPOSE 3000

CMD ["npm", "start"]