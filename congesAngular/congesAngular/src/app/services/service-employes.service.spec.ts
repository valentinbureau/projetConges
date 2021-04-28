import { TestBed } from '@angular/core/testing';

import { ServiceEmployesService } from './service-employes.service';

describe('ServiceEmployesService', () => {
  let service: ServiceEmployesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceEmployesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
