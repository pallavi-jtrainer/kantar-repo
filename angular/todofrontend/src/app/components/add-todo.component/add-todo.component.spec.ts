import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddTodoComponent } from './add-todo.component';
import { FormsModule } from '@angular/forms';
import { TodoService } from '../../services/todoservice';
import { of } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { By } from '@angular/platform-browser';

describe('AddTodoComponent', () => {
  let component: AddTodoComponent;
  let fixture: ComponentFixture<AddTodoComponent>;
  let mockService: jasmine.SpyObj<TodoService>;

  beforeEach(async () => {
    mockService = jasmine.createSpyObj<TodoService>('TodoService', ['createTodo']);

    await TestBed.configureTestingModule({
      imports: [AddTodoComponent, FormsModule],
      providers: [
        { provide: TodoService, useValue: mockService },
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: { params: { id: 1 } }
          }
        }
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AddTodoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create component and extract userId from route', () => {
    expect(component).toBeTruthy();
    expect(component.userId).toBe(1);
  });

  it('should call the service and create a new todo', () => {
    const mockTodo = { userId: 1, title: 'New todo', completed: false }

    mockService.createTodo.and.returnValue(of(mockTodo))

    const inputElement = fixture.debugElement.query(By.css('input')).nativeElement;
    const buttonElement = fixture.debugElement.query(By.css('button')).nativeElement;

    // Simulate user typing
    inputElement.value = 'New todo';
    inputElement.dispatchEvent(new Event('input'));
    fixture.detectChanges();

    // Click button
    buttonElement.click();
    fixture.detectChanges();

    component.userId = 1;
    // component.id = 3;
    component.title = 'New todo';
    component.addTodo();

    expect(mockService.createTodo).toHaveBeenCalledOnceWith(mockTodo);
    // expect(mockTodo.title).toEqual('New todo');
  })
});
