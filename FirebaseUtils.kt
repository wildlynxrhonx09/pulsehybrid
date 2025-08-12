package com.pulsehybridx.app.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.pulsehybridx.app.model.UserLocation

object FirebaseUtils {
    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun register(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            callback(it.isSuccessful, it.exception?.localizedMessage)
        }
    }

    fun signIn(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            callback(it.isSuccessful, it.exception?.localizedMessage)
        }
    }

    fun logout() {
        auth.signOut()
    }

    fun currentUid(): String? = auth.currentUser?.uid

    fun updateUserLocation(location: UserLocation) {
        currentUid()?.let { uid ->
            db.collection("locations").document(uid).set(location)
            db.collection("location_history").document(uid)
                .collection("entries").add(location)
        }
    }

    fun listenToUserLocations(callback: (String, UserLocation) -> Unit) {
        db.collection("locations").addSnapshotListener { snapshot, _ ->
            snapshot?.documents?.forEach { doc ->
                val loc = doc.toObject(UserLocation::class.java)
                if (loc != null) callback(doc.id, loc)
            }
        }
    }
}
