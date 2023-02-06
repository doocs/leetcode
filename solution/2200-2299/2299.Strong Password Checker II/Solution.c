bool strongPasswordCheckerII(char* password) {
    int n = strlen(password);
    if (n < 8) {
        return false;
    }
    int mask = 0;
    char prev = ' ';
    for (int i = 0; i < n; i++) {
        if (prev == password[i]) {
            return false;
        }
        if (islower(password[i])) {
            mask |= 0b1000;
        } else if (isupper(password[i])) {
            mask |= 0b100;
        } else if (isdigit(password[i])) {
            mask |= 0b10;
        } else {
            mask |= 0b1;
        }
        prev = password[i];
    }
    return mask == 0b1111;
}
