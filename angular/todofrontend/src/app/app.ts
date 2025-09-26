import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// import { ListTodos } from './components/list-todos/list-todos';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  // template: `<div>{{title}}</div>`,
  styleUrl: './app.css'
})
export class App {
  // protected readonly title = signal('todofrontend');

  protected readonly title = 'Todo Frontend';
}
