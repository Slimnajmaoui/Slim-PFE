



import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router } from '@angular/router';
import { AfftacheService } from '../afftache.service';
import { Afftache } from '../afftache';
import { ReactiveFormsModule } from '@angular/forms';
import { UserfooterComponent } from '../../user/userfooter/userfooter.component';
import { UserheaderComponent } from '../../user/userheader/userheader.component';
import { UsermenuComponent } from '../../user/usermenu/usermenu.component';



@Component({
  selector: 'app-userindexafftache',
  standalone: true,
  imports: [CommonModule, RouterModule, ReactiveFormsModule,UserfooterComponent,UserheaderComponent,UsermenuComponent],
  templateUrl: './userindexafftache.component.html',
  styleUrl: './userindexafftache.component.css'
})
export class UserindexafftacheComponent {
  afftaches: Afftache[] = [];
    
 
  constructor(public afftacheService: AfftacheService, private router: Router) { }
    
  /**
   * Write code on Method
   *
   * @return response()
   */
  ngOnInit(): void {
    console.log(this.router.url);
    console.log( window.location.href);
    this.afftacheService.getAll().subscribe((data: Afftache[])=>{
      this.afftaches = data;
      console.log(this.afftaches);
    })  
  }
    
  /**
   * Write code on Method
   *
   * @return response()
   */
  deleteAfftache(id:number){
    this.afftacheService.delete(id).subscribe(res => {
         this.afftaches = this.afftaches.filter(item => item._id !== id);
         console.log('Afftache deleted successfully!');
    })
  }
}
