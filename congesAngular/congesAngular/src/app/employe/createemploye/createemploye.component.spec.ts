import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateemployeComponent } from './createemploye.component';

describe('CreateemployeComponent', () => {
  let component: CreateemployeComponent;
  let fixture: ComponentFixture<CreateemployeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateemployeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateemployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
