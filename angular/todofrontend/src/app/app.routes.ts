import { Routes } from '@angular/router';
import { ListTodos } from './components/list-todos/list-todos';
import { ViewTodo } from './components/view-todo/view-todo.component';
import { AddTodoComponent } from './components/add-todo.component/add-todo.component';
import { AuthGuard } from './auth-guard';
import { Login } from './components/login/login';
import { Register } from './components/register/register';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: 'list', component: ListTodos, canActivate: [AuthGuard] },
  { path: "view/:id", component: ViewTodo },
  { path: 'add', component: AddTodoComponent }

];
