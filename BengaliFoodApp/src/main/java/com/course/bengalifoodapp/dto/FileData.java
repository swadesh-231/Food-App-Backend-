package com.course.bengalifoodapp.dto;

//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class FileData {
//    private String fileName;
//    private String filePath;
//}

public record FileData(String filename, String filePath) {

}