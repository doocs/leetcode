function validPalindrome(s: string): boolean {
    const check = (i: number, j: number): boolean => {
        for (; i < j; ++i, --j) {
            if (s[i] !== s[j]) {
                return false;
            }
        }
        return true;
    };
    for (let i = 0, j = s.length - 1; i < j; ++i, --j) {
        if (s[i] !== s[j]) {
            return check(i + 1, j) || check(i, j - 1);
        }
    }
    return true;
}
