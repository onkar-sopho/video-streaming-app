import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UploadVideoResponse} from "./upload-video/UploadVideoResponse";

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File): Observable<UploadVideoResponse> {
    const formData = new FormData();
    formData.append('file', fileEntry, fileEntry.name);

    // http post call to backend api to upload the video
    return this.httpClient.post<UploadVideoResponse>("http://localhost:8081/api/videos", formData);
  }
}
