# Lerni Spring Service

## Setup

Before running the application, you need to create a `.env` file in the root directory of the project. This file should contain the following:

```
JWT_SECRET=samelernisecret
GITLAB_TOKEN=token
```

## Docker

To run the application in a docker container, you need to have docker installed on your machine. Then, you can run the following commands:

```
docker compose up --build
```