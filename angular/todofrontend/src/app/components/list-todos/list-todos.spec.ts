import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTodos } from './list-todos';
import { TodoService } from '../../services/todoservice';
import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting } from '@angular/common/http/testing';
import { ActivatedRoute, provideRouter, Router } from '@angular/router';
import { of } from 'rxjs';

describe('ListTodos', () => {
  let component: ListTodos;
  let fixture: ComponentFixture<ListTodos>;
  let serviceSpy: jasmine.SpyObj<TodoService>;
  let router: Router;

  beforeEach(async () => {
    serviceSpy = jasmine.createSpyObj<TodoService>('TodoService', ['listAllTodos', 'listTodosForUser']);
    serviceSpy.listAllTodos.and.returnValue(of([
      { id: 1, userId: 1, title: 'first', completed: false },
      { id: 2, userId: 2, title: 'second', completed: false }
    ]));
    serviceSpy.listTodosForUser.and.returnValue(of([
      { id: 1, userId: 1, title: 'todo 1', completed: true },
      { id: 2, userId: 1, title: 'todo 2', completed: false }
    ]));
    await TestBed.configureTestingModule({
      imports: [ListTodos],
      providers: [
        // provideHttpClient(),
        // provideHttpClientTesting(),
        provideRouter([]),
        { provide: TodoService, useValue: serviceSpy },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot:
              // { paramMap: new Map([['id', 1]]) }
              { params: { id: '1' } }
          }
        },

      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ListTodos);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);

    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should load all todos when userId is zero', () => {
    component.id = 0;
    component.loadTodos();
    expect(serviceSpy.listAllTodos).toHaveBeenCalled();
    expect(component.todos.length).toBe(2);
  });

  it('should load todos for a specific user when userId is present', () => {
    component.id = 1;
    component.loadTodos();
    expect(serviceSpy.listTodosForUser).toHaveBeenCalledWith(1);
    expect(component.todos.length).toBe(2);
    expect(component.todos[0].title).toBe('todo 1');
  });

  it('should navigate to todo detail when navigateToViewTodo() is called', () => {
    spyOn(router, 'navigate');
    component.navigateToViewTodo(1);
    expect(router.navigate).toHaveBeenCalledWith(['view/1']);
  });
});


