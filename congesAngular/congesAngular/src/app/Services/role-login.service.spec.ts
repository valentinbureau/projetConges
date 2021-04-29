import { TestBed } from '@angular/core/testing';

import { RoleLoginService } from './role-login.service';

describe('RoleLoginService', () => {
  let service: RoleLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoleLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
