import { Component, computed, inject } from '@angular/core';
import { Todo } from '../../models/Todo';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { TodoService } from '../../services/todoservice';

@Component({
  selector: 'app-view-todo',
  imports: [CommonModule],
  templateUrl: './view-todo.component.html',
  styleUrl: './view-todo.component.css'
})
export class ViewTodo {

  // todo: Todo | null | undefined;
  /**
   * constructor injection approach - old way
   */
  // constructor(private route: ActivatedRoute, private todoService: TodoService,
  //   private router: Router
  // ) { }

  /**
   * inject function approach - new way (Angular 16+)
   */
  private route = inject(ActivatedRoute);
  private todoService = inject(TodoService);
  private router = inject(Router);

  todo: Todo = {
    userId: 0,
    title: '',
    completed: false
  };
  //Reactive computed property
  // todo = computed(() => this.todoService.selectedTodo());

  id: any;
  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.loadTodo();
  }

  loadTodo() {
    /**
     * using signal approach
     */
    // this.todoService.loadTodoById(id);

    /**
     * using promise approach
     */
    // this.todoService.getTodo(id).then((data: any) => {
    //   this.todo = data;
    //   console.log(this.todo);
    // }).catch((err: any) => {
    //   console.log(err);
    // });

    /**
     * using observable approach
     */
    this.todoService.getTodo(this.id).subscribe(
      {
        next: (data: any) => {
          this.todo = data;
          console.log(this.todo);
        },
        error: (err: any) => {
          console.log(err);
        }
      });
  }

  backToList() {
    this.router.navigate(['/list']);
  }
}
