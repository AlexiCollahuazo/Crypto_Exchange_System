package com.globant.controller.MenuUser;

import com.globant.controller.ControllerExecuteInterface;
import com.globant.model.user.User;
import com.globant.service.user.UnknownUserException;
import com.globant.model.user.UserSingleton;
import com.globant.view.MenuUserView;

 public class RegisterController implements ControllerExecuteInterface {
    private final MenuUserView view;
   private final UserAuthenticationService userauthn;

     public RegisterController(MenuUserView view, UserAuthenticationService userAuthenticationService) {
         this.view = view;
         this.userauthn = userAuthenticationService;
     }

     public void execute() {
         String[] details = view.getRegisterView();
         for (String detail : details) {
             if (detail.isEmpty()) {
                 view.showError("There can be no empty spaces");
                 return;
             }
         }
         try {
             if (userauthn.VerifyEmail(details[1])) {
                 view.showError("Email Registered, Try another One");
             } else {
                 User user = new User(details[0], details[1], details[2]);
                 userauthn.registerUser(user);
                 int userId = user.getID();
                 view.showUserId("USER ID IS: ", userId);
                 view.showSuccessMessage("User Registered Successfully");
             }
         } catch (UnknownUserException e) {
             view.showError("Error in registration");

         }


         }

     }




