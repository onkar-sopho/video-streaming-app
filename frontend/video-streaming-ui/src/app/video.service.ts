import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);

    // http post call to backend api to upload the video
    return this.httpClient.post("http://localhost:8081/api/videos", formData);
  }
}
