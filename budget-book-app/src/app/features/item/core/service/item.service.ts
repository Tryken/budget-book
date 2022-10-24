import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { BBItem, BBItemList } from "../model";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../../../environments/environment";
import { BBTableFilter } from "../../../../common/table/table/model/table-filter.model";

@Injectable({
  providedIn: "root"
})
export class BBItemService {
  constructor(private http: HttpClient) {}

  public getList(tableFilter: BBTableFilter): Observable<BBItemList> {
    return this.http.get<BBItemList>(
      `${environment.services.itemService.baseUrl}/item`
    );
  }

  public update(item: BBItem) {
    return this.http.put<BBItemList>(
      `${environment.services.itemService.baseUrl}/item/${item.id}`,
      item
    );
  }

  public save(item: BBItem) {
    return this.http.post<BBItemList>(
      `${environment.services.itemService.baseUrl}/item`,
      item
    );
  }

  public delete(item: BBItem) {
    return this.http.delete<BBItemList>(
      `${environment.services.itemService.baseUrl}/item/${item.id}`
    );
  }
}
