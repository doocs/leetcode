function maxVowels(s: string, k: number): number {
    function isVowel(c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    let t = 0;
    for (let i = 0; i < k; ++i) {
        if (isVowel(s.charAt(i))) {
            t++;
        }
    }
    let ans = t;
    for (let i = k; i < s.length; ++i) {
        if (isVowel(s.charAt(i))) {
            t++;
        }
        if (isVowel(s.charAt(i - k))) {
            t--;
        }
        ans = Math.max(ans, t);
    }
    return ans;
}
