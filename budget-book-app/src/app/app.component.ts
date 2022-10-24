import { Component, OnInit } from "@angular/core";

import { PrimeNGConfig } from "primeng/api";

@Component({
  selector: "bb-root",
  templateUrl: "./app.component.html",
  styleUrls: ["./app.component.scss"]
})
export class BBAppComponent implements OnInit {
  title = "budget-book-app";

  constructor(private primengConfig: PrimeNGConfig) {}

  ngOnInit(): void {
    this.primengConfig.ripple = true;
  }
}
