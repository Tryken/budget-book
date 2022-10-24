export interface ItemService {
  baseUrl: String;
}

export interface Services {
  itemService: ItemService;
}

export interface Environment {
  production: boolean;
  services: Services;
}
