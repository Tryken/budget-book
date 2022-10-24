import { Environment } from "./environment.model";

export const environment: Environment = {
  production: true,
  services: {
    itemService: {
      baseUrl: "http://localhost:8080/api/"
    }
  }
};
