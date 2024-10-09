function countOfSubstrings(word: string, k: number): number {
    const f = (k: number): number => {
        let ans = 0;
        let l = 0,
            x = 0;
        const cnt = new Map<string, number>();

        const vowel = (c: string): boolean => {
            return c === 'a' || c === 'e' || c === 'i' || c === 'o' || c === 'u';
        };

        for (const c of word) {
            if (vowel(c)) {
                cnt.set(c, (cnt.get(c) || 0) + 1);
            } else {
                x++;
            }

            while (x >= k && cnt.size === 5) {
                const d = word[l++];
                if (vowel(d)) {
                    cnt.set(d, cnt.get(d)! - 1);
                    if (cnt.get(d) === 0) {
                        cnt.delete(d);
                    }
                } else {
                    x--;
                }
            }
            ans += l;
        }

        return ans;
    };

    return f(k) - f(k + 1);
}
