import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { BBTableComponent } from "./table.component";
import { TableModule } from "primeng/table";
import { ToolbarModule } from "primeng/toolbar";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";
import { CardModule } from "primeng/card";

@NgModule({
  declarations: [BBTableComponent],
  exports: [BBTableComponent],
  imports: [
    CommonModule,
    TableModule,
    ToolbarModule,
    ButtonModule,
    RippleModule,
    CardModule
  ]
})
export class BBTableModule {}
