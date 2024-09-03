import {Component, OnInit} from '@angular/core';
import {NgForOf} from "@angular/common";
import {UsersService} from "../services/services/users.service";
import {User} from "../services/models/user";

@Component({
  selector: 'app-list-online-users',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './list-online-users.component.html',
  styleUrl: './list-online-users.component.scss'
})
export class ListOnlineUsersComponent implements OnInit {

  public onlineUsers: User[] = [];

  constructor(private userService: UsersService) {}

  ngOnInit(): void {
    this.userService.findConnectedUsers().subscribe({
      next: (data) =>   { this.onlineUsers = data; },
      error: (error) => { console.error(error); }
    })
  }
}
