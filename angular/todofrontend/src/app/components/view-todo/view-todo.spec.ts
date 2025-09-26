import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTodo } from './view-todo.component';
import { TodoService } from '../../services/todoservice';
import { ActivatedRoute, Router } from '@angular/router';
import { of } from 'rxjs';

describe('ViewTodo', () => {
  let component: ViewTodo;
  let fixture: ComponentFixture<ViewTodo>;
  let mockService: jasmine.SpyObj<TodoService>;
  let router: Router;

  beforeEach(async () => {
    mockService = jasmine.createSpyObj<TodoService>('TodoService', ['getTodo']);
    mockService.getTodo.and.returnValue(of(
      { id: 1, userId: 2, title: 'anything', completed: false }));

    await TestBed.configureTestingModule({
      imports: [ViewTodo],
      providers: [
        { provide: TodoService, useValue: mockService },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot:
              { paramMap: new Map([['id', '1']]) }
          }
        }
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ViewTodo);
    component = fixture.componentInstance;
    router = TestBed.inject(Router);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should fetch todo based on id', () => {
    component.id = 1;
    component.loadTodo();
    expect(mockService.getTodo).toHaveBeenCalledWith(1);
    expect(component.todo.title).toEqual('anything');
  })
});
