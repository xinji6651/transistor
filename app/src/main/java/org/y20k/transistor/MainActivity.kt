/*
 * MainActivity.kt
 * Implements the MainActivity class
 * MainActivity just extends BaseMainActivity - the non-free version adds a Cast button listener here
 *
 * This file is part of
 * TRANSISTOR - Radio App for Android
 *
 * Copyright (c) 2015-25 - Y20K.org
 * Licensed under the MIT-License
 * http://opensource.org/licenses/MIT
 */


package org.y20k.transistor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.mediarouter.app.MediaRouteButton
import com.google.android.gms.cast.framework.CastButtonFactory
import com.google.android.gms.cast.framework.CastContext


/*
 * MainActivity class
 */
class MainActivity : BaseMainActivity() {

    /* Define log tag */
    private val TAG: String = PlayerFragment::class.java.simpleName


    /* Main class variables */
    private var castContext: CastContext? = null
    lateinit var castButton: MediaRouteButton


    /* Overrides onCreate from BasePlayerFragment */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // initialize Cast context
        try {
            castContext = CastContext.getSharedInstance(this)
        } catch (e: Exception) {
            Log.e(TAG, "Cast framework unavailable", e)
        }

        // initialize cast button
        castButton = layout.rootView.findViewById(R.id.cast_button)
        if (castContext != null) {
            try {
                castButton.setRemoteIndicatorDrawable(AppCompatResources.getDrawable(this, R.drawable.selector_cast_button))
                CastButtonFactory.setUpMediaRouteButton(this, castButton)
                castButton.isVisible = true
            } catch (e: Exception) {
                Log.e(TAG, "Cast button setup failed.", e)
                castButton.isVisible = false
            }
        } else {
            castButton.isVisible = false
        }

    }

//    /*
//     * Inner class: listener that is called when the Cast state changes
//     */
//    private val customCastStateListener = object : CastStateListener {
//        override fun onCastStateChanged(state: Int) {
//            castEnabled = state != CastState.NO_DEVICES_AVAILABLE
//            changeCastButtonVisibility(castEnabled)
//        }
//    }
//    /*
//     * End of inner class
//     */

}