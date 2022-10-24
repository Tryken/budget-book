import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { BBItemListComponent } from "./item-list.component";
import { BBTableModule } from "../../../common/table/table";
import { ButtonModule } from "primeng/button";
import { RippleModule } from "primeng/ripple";
import { DialogModule } from "primeng/dialog";
import { InputTextModule } from "primeng/inputtext";
import { ReactiveFormsModule } from "@angular/forms";
import { InputTextareaModule } from "primeng/inputtextarea";
import { ConfirmationService, MessageService } from "primeng/api";

@NgModule({
  declarations: [BBItemListComponent],
  exports: [BBItemListComponent],
  imports: [
    CommonModule,
    BBTableModule,
    ButtonModule,
    RippleModule,
    DialogModule,
    InputTextModule,
    ReactiveFormsModule,
    InputTextareaModule
  ],
  providers: [ConfirmationService, MessageService]
})
export class BBItemListModule {}
