package com.mun0n.omdb.presentation;

import android.content.Context;

import com.mun0n.data.exception.MovieNotFoundException;
import com.mun0n.data.exception.NetworkConnectionException;
import com.mun0n.omdb.R;

public class ErrorMessageFactory {
    
    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);
        
        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof MovieNotFoundException) {
            message = context.getString(R.string.exception_message_user_not_found);
        }
        
        return message;
    }
    
}
