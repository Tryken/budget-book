import { NgModule } from "@angular/core";
import { BrowserModule } from "@angular/platform-browser";
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from "./app-routing.module";
import { BBAppComponent } from "./app.component";
import { BBItemModule } from "./features/item/item.module";
import { BBItemListModule } from "./features/item/item-list/item-list.module";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { ToastModule } from "primeng/toast";

@NgModule({
  declarations: [BBAppComponent],
  imports: [
    HttpClientModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    BBItemModule,
    BBItemListModule,
    ConfirmDialogModule,
    ToastModule
  ],
  bootstrap: [BBAppComponent]
})
export class BBAppModule {}
