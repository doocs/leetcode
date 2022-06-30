function longestPalindrome(s: string): string {
    const n = s.length;
    const isPass = (l: number, r: number) => {
        while (l < r) {
            if (s[l++] !== s[r--]) {
                return false;
            }
        }
        return true;
    };
    let res = s[0];
    for (let i = 0; i < n - 1; i++) {
        for (let j = n - 1; j > i; j--) {
            if (j - i < res.length) {
                break;
            }
            if (isPass(i, j)) {
                res = s.slice(i, j + 1);
            }
        }
    }
    return res;
}
