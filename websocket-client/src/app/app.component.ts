import {Component, OnDestroy, OnInit} from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {FormsModule} from "@angular/forms";
import {adjectives, colors, Config, uniqueNamesGenerator} from "unique-names-generator";
import {ToastrService} from "ngx-toastr";
import SockJS from "sockjs-client";
import Stomp, {Subscription} from "stompjs";
import {Client} from "stompjs";
import {HttpClient} from "@angular/common/http";
import {Notification} from "./notification";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule, DatePipe],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit, OnDestroy {

  public socketClient: Client | undefined;
  public listOfMessages: Array<Notification> = [];
  private subscription: Subscription | undefined;
  public username: string = '';
  public message: string = '';
  public receiver: string = '';
  public customConfig: Config = {
    dictionaries: [adjectives, colors],
    separator: '-',
    length: 2,
  };

  constructor(private toaster: ToastrService, private httpClient: HttpClient) {
  }


  ngOnInit(): void {
    this.listOfMessages = [];
    this.username = uniqueNamesGenerator(this.customConfig);
    let ws = new SockJS('http://localhost:8088/ws');
    this.socketClient = Stomp.over(ws);
    // If our server requires authentication, we can pass the credentials as follows:
    // this.socketClient.connect('username', 'password', () => {});
    // this.socketClient.connect({ 'Authorization': 'Bearer' + token }, () => {});
    this.socketClient.connect({}, this.onConnect.bind(this));
  }

  ngOnDestroy(): void {
    if (this.subscription)
      this.subscription.unsubscribe();
  }

  public onConnect(): void {
    console.log('Websocket Connection Established');
    this.subscription = this.socketClient?.subscribe(`/user/${this.username}/message`, this.onReceiveMessage.bind(this));
  }

  public sendMessage(): void {
    if (!this.receiver || this.receiver.length === 0) {
      this.toaster.error('Please enter a receiver');
      return;
    }
    if (!this.message || this.message.length === 0) {
      this.toaster.error('Please enter a message');
      return;
    }
    const requestBody = {
      message: this.message,
      senderUsername: this.username,
      receiverUsername: this.receiver
    }
    this.httpClient
      .post<any>('http://localhost:8088/message', requestBody)
      .subscribe(() => (this.toaster.success('Message Sent Successfully to ' + this.receiver)));

    this.listOfMessages.push({
      message: this.message,
      sender: this.username,
      createdAt: new Date().toISOString()
    });
    this.message = '';
  }

  public onReceiveMessage(response: any): void {
    if (!response) return;
    const body: Notification = JSON.parse(response.body);
    this.toaster.success(`You have a new message from ${body.sender}`);
    this.listOfMessages.push(body);
  }
}
