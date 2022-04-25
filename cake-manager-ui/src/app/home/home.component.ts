import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Cake } from '../shared/models/cake.model';
import { CakeManagerService } from '../shared/services/cake-manager.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  displayedColumns: string[] = ['title', 'desc', 'image'];
  dataSource: Cake[] = [];
  isAddActive: boolean = false;
  formGroup: FormGroup;
  constructor(
    private cakeService: CakeManagerService,
    private formBuilder: FormBuilder
  ) {
    this.formGroup = this.formBuilder.group({
      title: [null, Validators.required],
      desc: [null, Validators.required],
      image: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.updateAllCakes();
  }

  updateAllCakes() {
    this.cakeService.getAllCakes().subscribe(
      (data: any) => {
        this.dataSource = data;
      },
      (err) => {
        console.log(err.message);
      }
    );
  }
  downloadCakeJson() {
    this.cakeService.getAllCakesJson().subscribe(
      (data: any) => {
        var sJson = JSON.stringify(data);
        var element = document.createElement('a');
        element.setAttribute(
          'href',
          'data:text/json;charset=UTF-8,' + encodeURIComponent(sJson)
        );
        element.setAttribute('download', 'cakes.json');
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
      },
      (err) => {
        console.log(err.message);
      }
    );
  }

  showHideAddCakeForm() {
    this.isAddActive = !this.isAddActive;
  }

  onSubmit(formValue: Cake) {
    this.cakeService.addNewCake(formValue).subscribe(
      (data: any) => {
        console.log(data);
        this.updateAllCakes();
        this.isAddActive = false;
      },
      (err) => {
        console.log(err.message);
      }
    );
  }
}
