import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTodo } from './view-todo';

describe('ViewTodo', () => {
  let component: ViewTodo;
  let fixture: ComponentFixture<ViewTodo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewTodo]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewTodo);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
