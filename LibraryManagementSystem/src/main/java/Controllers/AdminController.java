package Controllers;

import Models.*;

public class AdminController {
    public Admin findAdminbyUsername(String username) {
        for (User admin : FileController.users) {
            if (admin.getUsername().equals(username) && (admin instanceof Admin))
            {
                return (Admin) admin;
            }

        }
        return null;
    }

}
