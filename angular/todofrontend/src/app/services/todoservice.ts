import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Todo } from '../models/Todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  baseUrl = 'https://jsonplaceholder.typicode.com';
  // http: any;

  constructor(private http: HttpClient) { }

  listAllTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.baseUrl}/todos`);
  }

  getTodo(id: number) {
    return this.http.get(`${this.baseUrl}/todos/${id}`);
  }
}
