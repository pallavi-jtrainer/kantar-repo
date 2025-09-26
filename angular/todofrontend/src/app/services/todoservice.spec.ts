import { TestBed } from '@angular/core/testing';

import { TodoService } from './todoservice';
import { HttpClientTestingModule, HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { Todo } from '../models/Todo';
import { provideHttpClient } from '@angular/common/http';

describe('TodoService', () => {
  let service: TodoService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        TodoService,
        provideHttpClient(),
        provideHttpClientTesting()

      ]
    });
    service = TestBed.inject(TodoService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch todos list', () => {
    let mockTodos: Todo[] = [
      { id: 1, userId: 1, title: 'Testing with Jasmine', completed: false },
      { id: 2, userId: 3, title: 'writing test cases', completed: false }];

    service.listAllTodos()
      .subscribe((todos) => {
        expect(todos.length).toBe(2);
        expect(todos).toEqual(mockTodos);
      })

    const req = httpMock.expectOne('http://localhost:8090/api/todos');
    expect(req.request.method).toBe('GET');
    req.flush(mockTodos);
  });

  it('should create a new todo', () => {
    const todo: Todo = { userId: 2, title: '', completed: false }
    service.createTodo(todo).subscribe((newTodo) => {
      expect(newTodo).toEqual(todo);
    });

    const req = httpMock.expectOne('http://localhost:8090/api/todos');
    expect(req.request.method).toBe('POST');
    req.flush(todo);
  });

  it('should fetch todos list for a user', () => {
    let mockTodos: Todo[] = [
      { id: 1, userId: 1, title: 'Testing with Jasmine', completed: false },
      { id: 2, userId: 1, title: 'writing test cases', completed: false }];

    service.listTodosForUser(1)
      .subscribe((todos) => {
        expect(todos.length).toBe(2);
        expect(todos).toEqual(mockTodos);
      })

    const req = httpMock.expectOne('http://localhost:8090/api/todos/user/1');
    expect(req.request.method).toBe('GET');
    req.flush(mockTodos);
  });

  it('should fetch a single todo', () => {
    const todo: Todo = { id: 1, userId: 2, title: 'Anything', completed: false }

    service.getTodo(1).subscribe((val) => {
      expect(val).toEqual(todo);
    })

    const req = httpMock.expectOne('http://localhost:8090/api/todos/1');
    expect(req.request.method).toBe('GET');
    req.flush(todo);
  });
});
