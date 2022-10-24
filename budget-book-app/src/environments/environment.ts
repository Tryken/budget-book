import { Environment } from "./environment.model";

export const environment: Environment = {
  production: false,
  services: {
    itemService: {
      baseUrl: "http://localhost:8080/api"
    }
  }
};
