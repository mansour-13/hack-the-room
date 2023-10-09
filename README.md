# "Hack the Room" Web Application - Frontend

**"Hack the Room"** is a web application project that aims to provide an interactive learning experience through
gamification. The project leverages the GPT-3 API to create educational games that teach users various topics, with a
focus on coding challenges.

## Introduction

Welcome to the frontend part of the "Hack the Room" project! This section of the project is responsible for delivering
an engaging user interface and interactive experiences for our educational games. In this document, you will find an
overview of the core components and functionalities of the frontend.

- **Backend Repository**: Services in the frontend are responsible for making API calls to the backend (located in
  the [api-hack-the-room repository](https://github.com/your-github-username/api-hack-the-room)), handling user
  authentication, and managing game state.
- **Current URL**: The frontend of the "Hack the Room" web application is currently hosted
  at [https://hack-the-room.bulbt.com](https://hack-the-room.bulbt.com). You can access the live version of the
  application at this URL.

## Prerequisites

Before diving into the project, make sure you have the following prerequisites installed on your system:

- [Node.js](https://nodejs.org/) - Required for running Angular applications.
- [Angular CLI](https://cli.angular.io/) - A command-line tool for working with Angular projects.

## Installation

To get the frontend up and running on your local machine, follow these simple steps:

1. **Clone the Project**: Begin by cloning the project repository to your local machine.

2. **Navigate to the Frontend Directory**: Open a terminal and navigate to the project's frontend directory.

3. **Install Dependencies**: Run `npm install` to install all the required dependencies for the project.

4. **Start the Development Server**: Use the command `ng serve` to start a development server. The application will be
   accessible at `http://localhost:4200/`.

## Project Structure

The frontend is built using Angular, TypeScript, and HTML/CSS. It consists of various components that handle different
parts of the application, such as user registration, login, and the actual escape room challenges. Some of the key components include:

- **HomeComponent:** The main landing page of the application.
- **AboutComponent:** An about page with information about the project.
- **LoginComponent:** A page for user login.
- **RegisterComponent:** A page for user registration.
- **EscapeRoomComponent:** The main page for the game.
- **HighScoreComponent:** A page displaying high scores.
- **UserComponent:** A user profile page.
- **ActualGameLevelComponent:** A page for playing a specific game level.
-
The application uses Angular's built-in routing to navigate between different components and views. The routing
configuration can be found in the `app-routing.module.ts` file.

Thank you for being a part of the "Hack the Room" project. If you have any questions or need assistance, please feel
free to reach out.

