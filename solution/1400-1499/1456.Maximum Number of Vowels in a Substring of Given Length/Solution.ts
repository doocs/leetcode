function maxVowels(s: string, k: number): number {
    const isVowel = (c: string) => ['a', 'e', 'i', 'o', 'u'].includes(c);
    let cnt = 0;
    for (let i = 0; i < k; i++) {
        if (isVowel(s[i])) {
            cnt++;
        }
    }
    let ans = cnt;
    for (let i = k; i < s.length; i++) {
        if (isVowel(s[i])) {
            cnt++;
        }
        if (isVowel(s[i - k])) {
            cnt--;
        }
        ans = Math.max(ans, cnt);
    }
    return ans;
}
