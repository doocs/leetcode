function findTheLongestSubstring(s: string): number {
    const vowels = 'aeiou';
    const d: number[] = Array(32).fill(1 << 29);
    d[0] = 0;
    let [ans, mask] = [0, 0];
    for (let i = 1; i <= s.length; i++) {
        const c = s[i - 1];
        for (let j = 0; j < 5; j++) {
            if (c === vowels[j]) {
                mask ^= 1 << j;
                break;
            }
        }
        ans = Math.max(ans, i - d[mask]);
        d[mask] = Math.min(d[mask], i);
    }
    return ans;
}
