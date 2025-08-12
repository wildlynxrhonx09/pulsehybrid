# PulseHybridX

PulseHybridX is a real-time location tracking Android app built with Kotlin, Firebase, and Google Maps.

## Features
- Firebase Auth (email/password)
- Firestore for realtime location sync
- Background location tracking
- Live map view with multiple users

## Setup
1. Create Firebase project → enable Auth + Firestore.
2. Download `google-services.json` → place in `app/`.
3. Get Google Maps Android API key → replace in `AndroidManifest.xml`.
4. Sync project in Android Studio → run.

## Firestore Structure
- `users/{uid}`
- `locations/{uid}`
- `location_history/{uid}/entries/{autoId}`
