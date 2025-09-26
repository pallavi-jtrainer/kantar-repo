import { Component } from '@angular/core';
import { Todo } from '../../models/Todo';
import { TodoService } from '../../services/todoservice';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-list-todos',
  imports: [CommonModule],
  templateUrl: './list-todos.html',
  styleUrl: './list-todos.css'
})
export class ListTodos {

  todos: Todo[] = [];

  id: number = 0;
  constructor(private todoService: TodoService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.id = Number(this.route.snapshot.params['id']);
    this.loadTodos(this.id);
  }

  loadTodos(id: number) {
    if (id === 0) {
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
    } else {
      this.todoService.listTodosForUser(id)
        .subscribe({
          next: (data) => {
            this.todos = data;
            console.log(this.todos);
          }, error: (err) => { console.log(err); }
        })
    }

  }

  navigateToViewTodo(id: number) {
    this.router.navigate([`view/${id}`]);
  }

  goToAddTodo() {
    this.router.navigate(['/add/' + this.id]);
  }
}
