package com.example.erkinbekovbilimdz_all_4.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.navigation.fragment.findNavController
import com.example.erkinbekovbilimdz_all_4.R
import com.example.erkinbekovbilimdz_all_4.databinding.FragmentAuthBinding
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider


class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        oneTapClient = Identity.getSignInClient(requireActivity())
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
        binding.btnAuth.setOnClickListener{
            auth()
        }
    }

    private fun auth() {
        oneTapClient.beginSignIn(signInRequest).addOnSuccessListener {
            startIntentSenderForResult(
                it.pendingIntent.intentSender, REQ_ONE_TAP,
                null, 0, 0, 0, null)
        }.addOnFailureListener {
            Log.e("moon", "auth: " + it.message)
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            REQ_ONE_TAP -> {
                try {
                    val credential = oneTapClient.getSignInCredentialFromIntent(data)
                    val idToken = credential.googleIdToken
                    when {
                        idToken != null -> {
                            val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                            auth.signInWithCredential(firebaseCredential)
                                .addOnSuccessListener {
                                    findNavController().navigateUp()
                                }.addOnFailureListener {
                                    Log.e("moon", "onActivityResult: " + it.message)
                                }
                        }
                        else -> {
                        }
                    }
                } catch (e: ApiException) {
                    Log.e("moon", "onActivityResult: " + e.message)
                }
            }
        }
    }
    companion object{
        const val REQ_ONE_TAP = 2
    }
}