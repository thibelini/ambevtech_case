import { Component, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { DeviceDetectorService } from 'ngx-device-detector';

@Component({
  selector: 'app-overlay',
  templateUrl: './overlay.component.html',
  styleUrls: ['./overlay.component.scss']
})
export class OverlayComponent {

  @Input() show = false;
  @Input() podeFechar = true;
  @Output() private showChange = new EventEmitter();
  @Output() onClose: EventEmitter<any> = new EventEmitter();

  desktop: boolean;

  
  constructor(private service: DeviceDetectorService) {
    this.desktop = this.service.isDesktop();
  }

  @HostListener('window:keydown.esc')
  close() {
      if (this.podeFechar) {
          this.show = false;
          this.showChange.emit(this.show);

          this.onClose.emit();
      }
  }

}
