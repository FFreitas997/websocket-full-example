/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { findConnectedUsers } from '../fn/users/find-connected-users';
import { FindConnectedUsers$Params } from '../fn/users/find-connected-users';
import { User } from '../models/user';


/**
 * APIs for managing users
 */
@Injectable({ providedIn: 'root' })
export class UsersService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `findConnectedUsers()` */
  static readonly FindConnectedUsersPath = '/users';

  /**
   * Find connected users.
   *
   * Find all connected users
   *
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `findConnectedUsers()` instead.
   *
   * This method doesn't expect any request body.
   */
  findConnectedUsers$Response(params?: FindConnectedUsers$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<User>>> {
    return findConnectedUsers(this.http, this.rootUrl, params, context);
  }

  /**
   * Find connected users.
   *
   * Find all connected users
   *
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `findConnectedUsers$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  findConnectedUsers(params?: FindConnectedUsers$Params, context?: HttpContext): Observable<Array<User>> {
    return this.findConnectedUsers$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<User>>): Array<User> => r.body)
    );
  }

}
