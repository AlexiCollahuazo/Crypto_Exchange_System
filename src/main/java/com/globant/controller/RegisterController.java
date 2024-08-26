package com.globant.controller;

import com.globant.model.user.User;
import com.globant.service.UserAuthentication;
import com.globant.service.UnknownUserException;
import com.globant.view.ConsoleView;

 class RegisterController implements ControllerExecuteInterface {
    private final ConsoleView view;
    private final UserAuthentication userauthn;

     public RegisterController(ConsoleView view, UserAuthentication userAuthentication) {
         this.view = view;
         this.userauthn = userAuthentication;
     }

     public void execute() {
          String[] details = view.getRegisterView();
         try
         {
             if (userauthn.VerifyEmail(details[1])) {
             view.showError("Email Registered, Try another One");
             } else {

             User user = new User(details[0], details[1], details[2]);
             userauthn.registerUser(user);
             view.showUserId(user.getID());
             view.showSuccessMessage("User Registered Successfully");
         }
      } catch (UnknownUserException e) {
          view.showError("Error in registration");

      }

     }



}
