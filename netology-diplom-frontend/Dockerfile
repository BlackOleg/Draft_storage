FROM node:current-alpine3.17
EXPOSE 3000
#WORKDIR /app
COPY package*.json .

RUN npm install
# Если вы создаете сборку для продакшн
# RUN npm ci --omit=dev

#RUN npm audit fix --force
#RUN npm build
COPY . .

CMD ["npm", "run", "serve"]
#ENTRYPOINT ["npm","run","start"]

