package com.globant.controller.MenuUser;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.user.User;
import com.globant.service.user.UserAuthenticationService;
import com.globant.service.user.UnknownUserException;
import com.globant.service.user.UserSingleton;
import com.globant.view.MenuUserView;

 public class RegisterController implements ControllerExecuteInterface {
    private final MenuUserView view;
    private final UserAuthenticationService userauthn;
    private User user;

     public RegisterController(MenuUserView view, UserAuthenticationService userAuthenticationService) {
         this.view = view;
         this.userauthn = userAuthenticationService;
         this.user = UserSingleton.getInstance().getCurrentUser();
     }

     public void execute() {
          String[] details = view.getRegisterView();
         try
         {
             if (userauthn.VerifyEmail(details[1]) ) {
             view.showError("Email Registered, Try another One");
             } else {
                 user = new User(details[0], details[1], details[2]);
             userauthn.registerUser(user);
             int userId = user.getID();

             view.showUserId("USER ID IS: ", userId );
             view.showSuccessMessage("User Registered Successfully");
         }
      } catch (UnknownUserException e) {
          view.showError("Error in registration");

      }

     }



}
