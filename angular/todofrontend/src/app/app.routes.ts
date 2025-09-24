import { Routes } from '@angular/router';
import { ListTodos } from './list-todos/list-todos';
import { ViewTodo } from './view-todo/view-todo';

export const routes: Routes = [
  { path: '', redirectTo: 'list', pathMatch: 'full' },
  { path: 'list', component: ListTodos },
  { path: ":id", component: ViewTodo }
];
