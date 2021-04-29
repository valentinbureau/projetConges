import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListcongesManagerComponent } from './listconges-manager.component';

describe('ListcongesManagerComponent', () => {
  let component: ListcongesManagerComponent;
  let fixture: ComponentFixture<ListcongesManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListcongesManagerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListcongesManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
