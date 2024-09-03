import { Component } from '@angular/core';
import {ListOnlineUsersComponent} from "../list-online-users/list-online-users.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-chat-room',
  standalone: true,
  imports: [
    ListOnlineUsersComponent,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './chat-room.component.html',
  styleUrl: './chat-room.component.scss'
})
export class ChatRoomComponent {

  public messageForm: {message: string} = {message: ''};

  sendMessage() {


  }
}
