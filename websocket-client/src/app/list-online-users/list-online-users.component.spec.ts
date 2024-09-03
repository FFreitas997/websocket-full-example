import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOnlineUsersComponent } from './list-online-users.component';

describe('ListOnlineUsersComponent', () => {
  let component: ListOnlineUsersComponent;
  let fixture: ComponentFixture<ListOnlineUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListOnlineUsersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListOnlineUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
