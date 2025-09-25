import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListTodos } from './list-todos';

describe('ListTodos', () => {
  let component: ListTodos;
  let fixture: ComponentFixture<ListTodos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListTodos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListTodos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
