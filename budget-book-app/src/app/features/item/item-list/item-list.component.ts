import { Component } from "@angular/core";
import { BBTableColumn } from "../../../common/table/table";
import { BBItem } from "../core/model/item.model";
import { BBTableFilter } from "../../../common/table/table/model/table-filter.model";
import { BBItemService } from "../core/service/item.service";
import { take } from "rxjs";
import { FormControl, FormGroup, Validators } from "@angular/forms";
import { ConfirmationService, MessageService } from "primeng/api";

@Component({
  selector: "bb-item-list",
  templateUrl: "./item-list.component.html",
  styleUrls: ["./item-list.component.scss"]
})
export class BBItemListComponent {
  private tableFilter!: BBTableFilter;

  public loading = true;
  public itemDialog = false;
  public columns: BBTableColumn[] = [
    {
      key: "name",
      displayName: "Name"
    },
    {
      key: "description",
      displayName: "Description"
    },
    {
      key: "eanCode",
      displayName: "EAN"
    }
  ];
  public items: BBItem[] = [];
  public itemDetailsForm = new FormGroup({
    id: new FormControl(""),
    name: new FormControl("", [
      Validators.required,
      Validators.minLength(3),
      Validators.maxLength(50)
    ]),
    description: new FormControl("", [Validators.maxLength(250)]),
    eanCode: new FormControl("", [
      Validators.minLength(8),
      Validators.maxLength(13)
    ])
  });

  constructor(
    private itemService: BBItemService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService
  ) {}

  public onApplyFilter(tableFilter: BBTableFilter) {
    this.tableFilter = tableFilter;
    this.loading = true;
    this.itemService
      .getList(tableFilter)
      .pipe(take(1))
      .subscribe((response) => {
        this.items = response.pageEntries;
        this.loading = false;
      });
  }

  public onOpenNew() {
    this.itemDetailsForm.patchValue({
      id: undefined,
      name: "",
      description: "",
      eanCode: undefined
    });
    this.itemDialog = true;
  }

  public onEdit(item: BBItem) {
    this.itemDetailsForm.patchValue({
      id: item.id,
      name: item.name,
      description: item.description,
      eanCode: item.eanCode
    });
    this.itemDialog = true;
  }

  public onHideDialog() {
    this.itemDialog = false;
  }

  public onSaveItem() {
    const item = this.itemDetailsForm.value as BBItem;

    if (item.id) {
      this.itemService
        .update(item)
        .pipe(take(1))
        .subscribe((response) => {
          this.itemDialog = false;
          this.onApplyFilter(this.tableFilter);
          this.messageService.add({
            severity: "success",
            summary: "Successful",
            detail: `Item Updated`,
            life: 3000
          });
        });
    } else {
      this.itemService
        .save(item)
        .pipe(take(1))
        .subscribe((response) => {
          this.itemDialog = false;
          this.onApplyFilter(this.tableFilter);
          this.messageService.add({
            severity: "success",
            summary: "Successful",
            detail: `Item Created`,
            life: 3000
          });
        });
    }
  }

  public delete(item: BBItem) {
    this.itemService
      .delete(item)
      .pipe(take(1))
      .subscribe((response) => {
        this.onApplyFilter(this.tableFilter);
        this.messageService.add({
          severity: "success",
          summary: "Successful",
          detail: `Item ${item.name} Deleted`,
          life: 3000
        });
      });
  }

  public onDelete(item: BBItem) {
    this.confirmationService.confirm({
      message: "Are you sure you want to delete the selected item?",
      header: "Confirm",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
        this.delete(item);
      }
    });
  }

  public onDeleteSelected(item: BBItem[]) {
    this.confirmationService.confirm({
      message: "Are you sure you want to delete the selected items?",
      header: "Confirm",
      icon: "pi pi-exclamation-triangle",
      accept: () => {
        item.forEach(this.delete.bind(this));
      }
    });
  }
}
