import { Component } from '@angular/core';
import { Todo } from '../models/Todo';
import { TodoService } from '../services/todoservice';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-todos',
  imports: [CommonModule],
  templateUrl: './list-todos.html',
  styleUrl: './list-todos.css'
})
export class ListTodos {

  todos: Todo[] = [];

  constructor(private todoService: TodoService, private router: Router) { }

  ngOnInit() {
    this.loadTodos();
  }

  loadTodos() {
    this.todoService.listAllTodos().subscribe(
      {
        next: (data: any) => {
          this.todos = data;
          console.log(this.todos);
        },
        error: (err: any) => {
          console.log(err);
        }
      });
  }

  navigateToViewTodo(id: number) {
    this.router.navigate([`/${id}`]);
  }
}
