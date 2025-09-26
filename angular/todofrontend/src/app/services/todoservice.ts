import { HttpClient } from '@angular/common/http';
import { Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { Todo } from '../models/Todo';

@Injectable({
  providedIn: 'root'
})
export class TodoService {
  // baseUrl = 'https://jsonplaceholder.typicode.com';
  baseUrl = "http://localhost:8090/api";

  constructor(private http: HttpClient) { }

  // todo = signal<Todo>({
  //   id: 0,
  //   userId: 0,
  //   title: '',
  //   completed: false
  // })

  selectedTodo = signal<Todo>({
    id: 0,
    userId: 0,
    title: '',
    completed: false
  });

  listAllTodos(): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.baseUrl}/todos`);
  }

  listTodosForUser(id: number): Observable<Todo[]> {
    return this.http.get<Todo[]>(`${this.baseUrl}/todos/user/${id}`);
  }

  // async getTodo(id: number) {
  //   return this.http.get(`${this.baseUrl}/todos/${id}`);
  // }

  getTodo(id: number): Observable<Todo> {
    return this.http.get<Todo>(`${this.baseUrl}/todos/${id}`);
  }

  /**
   * @param id - ID of the todo to be fetched
   * Fetches the todo by ID and updates the signal
   */
  // loadTodoById(id: number): void {
  //   this.http.get<Todo>(`${this.baseUrl}/todos/${id}`).subscribe(todo => {
  //     this.selectedTodo.set(todo);
  //   });
  // }

  createTodo(todo: Todo): Observable<Todo> {
    return this.http.post<Todo>(`${this.baseUrl}/todos`, todo);
  }
}
