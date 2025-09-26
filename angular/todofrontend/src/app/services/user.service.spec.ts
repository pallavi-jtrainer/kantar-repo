import { HttpTestingController, provideHttpClientTesting } from "@angular/common/http/testing";
import { UserService } from "./user.service"
import { TestBed } from "@angular/core/testing";
import { provideHttpClient } from "@angular/common/http";
import { inject } from "@angular/core";

let service: UserService;
let httpMock: HttpTestingController;

describe('UserService', () => {

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        UserService,
        provideHttpClient(),
        provideHttpClientTesting()
      ]
    });

    service = inject(UserService);
    httpMock = inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should fetch details for a single user by id', () => {
    let mockUser = { id: 1, name: 'Prithvi', username: 'prithvi', 'email': 'pri@jaja.c', password: 'pri123' }
  });

  it('should add a new user');

  it('should fetch details for a single user by email');

  it('should fetch details for a single user by username');


})
