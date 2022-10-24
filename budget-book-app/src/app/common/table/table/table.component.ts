import { Component, EventEmitter, Input, Output } from "@angular/core";

import { LazyLoadEvent } from "primeng/api";

import { BBTableColumn } from "./model";

@Component({
  selector: "bb-table",
  templateUrl: "./table.component.html",
  styleUrls: ["./table.component.scss"]
})
export class BBTableComponent {
  @Input() sortable = true;
  @Input() pageable = true;
  @Input() selectable = false;
  @Input() creatable = false;
  @Input() editable = false;
  @Input() deletable = false;
  @Input() exportable = false;

  @Input() loading = false;
  @Input() columns!: BBTableColumn[];
  @Input() rows!: any[];
  @Input() selectedRows!: any[];

  @Output() openNew = new EventEmitter<void>();
  @Output() applyFilter = new EventEmitter<any>();
  @Output() edit = new EventEmitter<any>();
  @Output() delete = new EventEmitter<any>();
  @Output() deleteSelected = new EventEmitter<any>();

  constructor() {}

  private sortOrderToString(sortOrder?: number): string | undefined {
    switch (sortOrder) {
      case 0:
        return undefined;
      case 1:
        return "asc";
      case 2:
        return "desc";
      default:
        return undefined;
    }
  }

  public onLazyLoad(event: LazyLoadEvent) {
    this.applyFilter.emit({
      page: event?.first,
      pageSize: event?.rows,
      sortField: event?.sortField,
      sortOrder: this.sortOrderToString(event?.sortOrder)
    });
  }

  public onOpenNew() {
    this.openNew.emit();
  }

  public onEdit(item: any) {
    this.edit.emit(item);
  }

  public onDelete(item: any) {
    this.delete.emit(item);
  }

  public onDeleteSelected() {
    this.deleteSelected.emit(this.selectedRows);
  }
}
