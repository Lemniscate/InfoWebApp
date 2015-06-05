```
{
  "family": "InfoApp",
  "containerDefinitions": [
    {
      "name": "info-webapp",
      "image": "lemniscate/info-webapp",
      "cpu": "512",
      "memory": "256",
      "entryPoint": [
        "java -Djava.security.egd=file:/dev/./urandom -jar /app.jar"
      ],
      "environment": [
        {
          "name": "ENVVAR1",
          "value": "VALUE1"
        }
      ],
      "command": [],
      "portMappings": [
        {
          "hostPort": "80",
          "containerPort": "8080"
        }
      ],
      "volumesFrom": [],
      "links": [],
      "mountPoints": [
        { }
      ],
      "essential": true
    }
  ],
  "volumes": []
}
```