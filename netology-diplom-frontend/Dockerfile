FROM node:19.7.0-alpine
#WORKDIR /app
COPY package*.json ./
EXPOSE 3000
RUN npm install
# Если вы создаете сборку для продакшн
# RUN npm ci --omit=dev
#RUN npm audit fix
#RUN npm build
COPY . .

#CMD ["npm", "run", "serve"]
ENTRYPOINT ["npm","run","start"]

