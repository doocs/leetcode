function makePalindrome(s: string): boolean {
    let cnt = 0;
    let i = 0;
    let j = s.length - 1;
    for (; i < j; ++i, --j) {
        if (s[i] != s[j]) {
            ++cnt;
        }
    }
    return cnt <= 2;
}
