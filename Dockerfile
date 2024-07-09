# Use Node.js version 14 as the base image
FROM node:14

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json (if present) to the working directory
COPY package*.json ./

# Install npm dependencies
RUN npm install

# Copy all files from the current directory to the working directory in the container
COPY . .

# Expose port 80 to allow communication to/from the container
EXPOSE 80

# Specify the command to run the application
CMD ["npm", "start"]
