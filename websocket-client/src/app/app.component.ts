import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {CreateChatroomComponent} from "./create-chatroom/create-chatroom.component";
import {ChatRoomComponent} from "./chat-room/chat-room.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CreateChatroomComponent, ChatRoomComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

}
