import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";

@Component({
  selector: 'app-create-chatroom',
  standalone: true,
  imports: [
    FormsModule
  ],
  templateUrl: './create-chatroom.component.html',
  styleUrl: './create-chatroom.component.scss'
})
export class CreateChatroomComponent {

  public chatroomForm: {nickName: string, realName: string} = {nickName: '', realName: ''};

  public createChatroom() {
    console.log('Create chatroom', this.chatroomForm);
  }

}
