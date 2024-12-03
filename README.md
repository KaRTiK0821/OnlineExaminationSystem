# Online Examination System

## Overview

The **Online Examination System** is a Java-based application designed for conducting online exams in an efficient and user-friendly manner. The system allows students to take exams, view their scores, and track their progress over time. The system is built using basic Object-Oriented Programming (OOP) concepts such as inheritance, encapsulation, and polymorphism.

## Features

### 1. **User Authentication**
   - Users (students and admin) can log in to the system with their credentials.
   - Different user roles provide varying levels of access (students can take exams, while admins can manage exams).

### 2. **Admin Features**
   - **Add, Edit, and Delete Exams**: Admin can create new exams, edit existing ones, or remove exams that are no longer needed.
   - **Manage Questions**: Admin can add questions, set correct answers, and specify answer options for each exam.
   - **View Student Results**: Admin can view results of all students who have taken exams.

### 3. **Student Features**
   - **Take Exams**: Students can choose from a list of available exams and take them.
   - **View Results**: After completing an exam, students can see their scores (number of correct answers, percentage score).
   - **Result Tracking**: Students can track their results over time as they take more exams.

### 4. **Exam Management**
   - **Randomized Question Order**: Questions are randomized each time the exam is taken to ensure fairness.
   - **Immediate Scoring**: After completing an exam, students get instant feedback on their performance.
   - **Result Storage**: Results are saved and associated with the student’s account for future reference.

## New Features Added

### 1. **Examination Timer (Initially Implemented)**
   - Initially, a timer feature was included, where students had a specific time limit to complete the exam.
   - However, the timer functionality was later removed based on feedback to allow students to take exams at their own pace.

### 2. **No Timer Mode (Current Version)**
   - The timer feature was removed to give students more time and flexibility to answer questions without the pressure of a countdown.
   - Students can now take as much time as needed to complete the exam.

### 3. **Randomized Question Selection**
   - To ensure fairness and prevent students from memorizing the order of questions, the order of questions in an exam is now randomized every time an exam is taken.

### 4. **Result Tracking and Storage**
   - Results are stored in the system, allowing students to track their performance across multiple exams.
   - Results are saved to a file and associated with the student’s account, ensuring that they can view their past performances.

## Technologies Used
- **Java**: Core programming language for implementing the system logic.
- **Object-Oriented Programming**: Used to structure the system with concepts like classes, objects, inheritance, and polymorphism.
- **File I/O**: For storing and retrieving user and result data from text files.

## Installation Guide

1. **Clone the repository**:
   ```bash
   git clone https://github.com/KaRTiK0821/online-examination-system.git
