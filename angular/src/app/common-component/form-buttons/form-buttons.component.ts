import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from 'stream';

@Component({
  selector: 'app-form-buttons',
  templateUrl: './form-buttons.component.html',
  styleUrls: ['./form-buttons.component.scss']
})
export class FormButtonsComponent implements OnInit {

  constructor(public vm:  ) { }
  @Input('send-disabled') sendDisabled: boolean | null = false;
  @Output() send: EventEmitter>any> = new EventEmitter();

  ngOnInit(): void {
  }

}
