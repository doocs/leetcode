function minimumDeletions(word: string, k: number): number {
    const freq: number[] = Array(26).fill(0);
    for (const ch of word) {
        ++freq[ch.charCodeAt(0) - 97];
    }
    const nums = freq.filter(x => x > 0);
    const f = (v: number): number => {
        let ans = 0;
        for (const x of nums) {
            if (x < v) {
                ans += x;
            } else if (x > v + k) {
                ans += x - v - k;
            }
        }
        return ans;
    };
    return Math.min(...Array.from({ length: word.length + 1 }, (_, i) => f(i)));
}
