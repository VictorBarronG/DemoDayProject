import { TestBed } from '@angular/core/testing';

import { SearchParamService } from './search-param.service';

describe('SearchParamService', () => {
  let service: SearchParamService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SearchParamService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
