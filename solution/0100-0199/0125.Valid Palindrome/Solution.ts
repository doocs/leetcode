function isPalindrome(s: string): boolean {
    let left: number = 0,
        right: number = s.length - 1;
    while (left < right) {
        let char1: string = s.charAt(left);
        let char2: string = s.charAt(right);
        if (!/[a-zA-Z0-9]/.test(char1)) {
            ++left;
        } else if (!/[a-zA-Z0-9]/.test(char2)) {
            --right;
        } else if (char1.toLocaleLowerCase() != char2.toLocaleLowerCase()) {
            return false;
        } else {
            ++left;
            --right;
        }
    }
    return true;
}
