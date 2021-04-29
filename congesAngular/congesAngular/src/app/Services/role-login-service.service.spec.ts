import { TestBed } from '@angular/core/testing';

import { RoleLoginServiceService } from './role-login-service.service';

describe('RoleLoginServiceService', () => {
  let service: RoleLoginServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoleLoginServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
