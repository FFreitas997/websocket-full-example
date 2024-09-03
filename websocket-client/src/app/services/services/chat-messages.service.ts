/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { ChatMessage } from '../models/chat-message';
import { getChatMessages } from '../fn/chat-messages/get-chat-messages';
import { GetChatMessages$Params } from '../fn/chat-messages/get-chat-messages';


/**
 * APIs for managing chat messages
 */
@Injectable({ providedIn: 'root' })
export class ChatMessagesService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getChatMessages()` */
  static readonly GetChatMessagesPath = '/messages/{senderId}/{recipientId}';

  /**
   * Find chat messages.
   *
   * Find all chat messages between two users
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getChatMessages()` instead.
   *
   * This method doesn't expect any request body.
   */
  getChatMessages$Response(params: GetChatMessages$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ChatMessage>>> {
    return getChatMessages(this.http, this.rootUrl, params, context);
  }

  /**
   * Find chat messages.
   *
   * Find all chat messages between two users
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getChatMessages$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getChatMessages(params: GetChatMessages$Params, context?: HttpContext): Observable<Array<ChatMessage>> {
    return this.getChatMessages$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ChatMessage>>): Array<ChatMessage> => r.body)
    );
  }

}
