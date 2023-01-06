import { Component, OnInit } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { ActivatedRoute } from "@angular/router";

export interface Item {
  title: string,
  description: string,
  price: number
}

export interface User {
  first_name: string,
  last_name: string,
  postal_code: number
}

@Component({
  selector: 'app-listing',
  templateUrl: './listing.component.html',
  styleUrls: ['./listing.component.css']
})
export class ListingComponent implements OnInit {
  id: any;
  title: string = "";
  description: string = "";
  price: string = "---.--";
  seller: string = "----- -----";
  postal_code: string = "00000";

  constructor(
    private http: HttpClient,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe(param => this.id = param["itemId"]);

    console.log(this.id)
  }

  ngOnInit(): void {
    this.http.get<Item>('http://localhost:8080/items/' + this.id.toString())
      .subscribe(item => {
        console.log(item);

        console.log(item.title);
        console.log(item.description);
        console.log(item.price);

        this.title = item.title;
        this.description = item.description;
        this.price = item.price.toString();
      },
      error => {
        window.location.replace("");
      });

    this.http.get<User>('http://localhost:8080/items/matchingUser/' + this.id.toString())
  .subscribe(user => {
        console.log(user.first_name);
        this.seller = user.first_name + ' ' + user.last_name;
        this.postal_code = user.postal_code.toString();
      },
      error => {
        window.location.replace("");
      });
  }

}
