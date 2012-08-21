package org.torpidity.core

class UserService {
    /**
     * Generate a new password hash and salt
     */
    void changePassword(user, newPassword) {
        user.passwordSalt = UUID.randomUUID().toString()
        user.passwordHash = hashPassword(user.email, newPassword,
            user.passwordSalt)
    }

    /**
     * Compute the password hash for the specified email, string password, and
     * salt
     */
    def hashPassword(emailAddress, passwordString, salt) {
        (passwordString + salt + emailAddress).encodeAsSHA1()
    }
}
