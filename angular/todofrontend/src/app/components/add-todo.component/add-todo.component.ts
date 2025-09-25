import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { TodoService } from '../../services/todoservice';
import { Router } from '@angular/router';
import { Todo } from '../../models/Todo';

@Component({
  selector: 'app-add-todo.component',
  imports: [FormsModule, CommonModule],
  templateUrl: './add-todo.component.html',
  styleUrl: './add-todo.component.css'
})
export class AddTodoComponent {

  constructor(private todoService: TodoService, private router: Router) { }

  todo?: Todo;
  id = 0;
  userId = 0;
  title = '';

  // ngOnInit() {
  //   this.addTodo();
  // }

  addTodo() {
    this.todo = {
      id: this.id,
      userId: this.userId,
      title: this.title,
      completed: false
    };
    this.todoService.createTodo(this.todo).subscribe({
      next: (data) => {
        console.log('Todo created successfully:', data);
        //   this.router.navigate(['/list']);
      },
      error: (error) => {
        console.error('Error creating todo:', error);
      }
    });
  }

  backToList() {
    this.router.navigate(['/list']);
  }
}
