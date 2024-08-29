package com.globant.controller.MenuUser;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.user.User;
import com.globant.service.user.UserAuthenticationService;
import com.globant.service.user.UnknownUserException;
import com.globant.view.ConsoleView;

 public class RegisterController implements ControllerExecuteInterface {
    private final ConsoleView view;
    private final UserAuthenticationService userauthn;

     public RegisterController(ConsoleView view, UserAuthenticationService userAuthenticationService) {
         this.view = view;
         this.userauthn = userAuthenticationService;
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
