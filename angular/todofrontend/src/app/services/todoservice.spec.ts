import { TestBed } from '@angular/core/testing';

import { Todoservice } from './todoservice';

describe('Todoservice', () => {
  let service: Todoservice;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Todoservice);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
