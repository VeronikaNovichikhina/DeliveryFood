# Food Delivery App (Android)

A mobile food delivery application for Android, built with a modern development stack.
The app gives users a clean, intuitive interface for browsing menus, searching dishes, adding items to a cart, and placing orders.

## Overview

The app supports user authentication and registration, with role separation between **administrators** and **regular users**:

- **Administrator** — can add new dishes to the catalog.
- **User** — can browse the catalog, search and filter items, and manage their cart.

## Motivation

The online food delivery market continues to grow rapidly, along with the number of people using mobile apps to order food. Many existing solutions are either overloaded with features or fail to provide a sufficiently simple, personalized experience. This project focuses on building a food delivery app centered on ease of use, making it relevant both as a practical product and as a case study in mobile app design.

## Project Goal

To develop a functional Android food delivery application using a modern technology stack (Kotlin, Jetpack Compose, Firebase) that provides smooth interaction with the product catalog, cart, authentication, and admin features.

## Objectives

1. Analyze existing solutions in the food delivery app space.
2. Justify the choice of technologies and architectural approach.
3. Design the application architecture using the MVVM pattern.
4. Design a Firebase Firestore database structure for products, cart, and user data.
5. Implement core features: authentication, catalog, cart, search, and filtering.
6. Test the application and evaluate the results.

## Scope

- **Object of study:** the process of developing a food delivery mobile application for Android.
- **Subject of study:** methods and tools for designing, architecting, and implementing the client side of the app using Kotlin, Jetpack Compose, and Firebase cloud services.

## Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| Dependency Injection | Hilt |
| Navigation | Navigation Compose |
| Async | Kotlin Coroutines |
| Authentication | Firebase Authentication |
| Database | Firebase Firestore |
| Animations | Lottie |

## Features

- 🔐 User registration and authentication (Firebase Authentication)
- 🛠️ Role-based access: admin vs. regular user
- 📋 Product catalog with search and filtering
- 🛒 Shopping cart management
- ☁️ Real-time data sync via Firebase Firestore
- 🎬 Smooth UI animations with Lottie

## Architecture

The application follows the **MVVM (Model-View-ViewModel)** pattern:

- **Model** — data layer, Firestore repositories
- **ViewModel** — business logic and state management, exposed via Kotlin Flows
- **View** — Jetpack Compose UI screens

Dependency injection is handled with **Hilt**, and navigation between screens is managed with **Navigation Compose**.

## Screenshot
<img width="216" height="480" alt="image" src="https://github.com/user-attachments/assets/5967de2c-6108-48ad-bdfc-e016da3ce7be" />
<img width="226" height="503" alt="image" src="https://github.com/user-attachments/assets/4d29fe09-8db7-4009-be60-2b407aad6d0f" />
<img width="221" height="491" alt="image" src="https://github.com/user-attachments/assets/2e755ffb-d624-44e7-9efe-ce0ddea45513" />
<img width="200" height="444" alt="image" src="https://github.com/user-attachments/assets/c7324fd5-55d3-4d79-b0b0-b5959081db26" />
<img width="210" height="467" alt="image" src="https://github.com/user-attachments/assets/42c782d5-2f39-4217-ac9f-8be181410c71" />
<img width="257" height="571" alt="image" src="https://github.com/user-attachments/assets/4d28299b-9496-403e-9d7c-a1d3ed4bb368" />
<img width="246" height="546" alt="image" src="https://github.com/user-attachments/assets/d963b91c-0c97-4e13-a132-d7145c2398f6" />
<img width="229" height="509" alt="image" src="https://github.com/user-attachments/assets/4184df57-2d85-4586-a30a-29ed214a2ed6" />
<img width="314" height="697" alt="image" src="https://github.com/user-attachments/assets/9d7b30e5-418d-4693-8183-5f91eac9e955" />
<img width="225" height="499" alt="image" src="https://github.com/user-attachments/assets/981cf05d-4241-4836-aecd-db4c2f5f9530" />
<img width="271" height="603" alt="image" src="https://github.com/user-attachments/assets/b7109669-5998-4c6f-b4a1-8db89675d725" />
<img width="256" height="569" alt="image" src="https://github.com/user-attachments/assets/492cbc94-01ec-457e-ad09-01ad2fd7980f" />

