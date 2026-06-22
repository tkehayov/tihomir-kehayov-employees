import { Component, signal, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatButtonModule } from '@angular/material/button';
import { MatGridListModule } from '@angular/material/grid-list';

export interface EmployeeCollaboration {
  employeeIdOne: number;
  employeeIdTwo: number;
  projectId: number;
  daysTogether: number;
}

@Component({
  selector: 'app-root',
  imports: [CommonModule, MatIconModule, MatDividerModule, MatButtonModule, MatGridListModule],
  templateUrl: './app.html',
  styleUrl: './app.css',
})
export class App {
  protected selectedFileName = signal('');

  protected responseData = signal<EmployeeCollaboration | null>(null);
  private http = inject(HttpClient);

  protected uploadFile(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input?.files?.[0];
    if (!file) return;

    this.selectedFileName.set(file.name);

    const formData = new FormData();
    formData.append('file', file);

    this.http.post<EmployeeCollaboration>('/employees/max-collaboration', formData).subscribe({
      next: (response) => {
        console.log('File uploaded successfully:', response);
        this.responseData.set(response);
      },
      error: (error) => console.error('Upload failed:', error),
    });
  }
}
