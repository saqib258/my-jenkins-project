package com.thesis.app;

/**
 * Basic Hello World application for DevSecOps Thesis Pipeline.
 * Used to verify Maven build and SonarQube static analysis.
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, DevSecOps World!");
        
        // Example of a code smell for SonarQube to find:
        // System.out.println("This is a hardcoded message");
    }
}
