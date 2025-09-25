import { Routes } from '@angular/router';
import { ListTodos } from './components/list-todos/list-todos';
import { ViewTodo } from './components/view-todo/view-todo.component';
import { AddTodoComponent } from './components/add-todo.component/add-todo.component';

export const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: ListTodos },
  { path: "view/:id", component: ViewTodo },
  { path: 'add', component: AddTodoComponent }
];
