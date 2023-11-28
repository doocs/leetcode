function beautifulSubstrings(s: string, k: number): number {
    const n = s.length;
    const vs: number[] = Array(26).fill(0);
    for (const c of 'aeiou') {
        vs[c.charCodeAt(0) - 'a'.charCodeAt(0)] = 1;
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let vowels = 0;
        for (let j = i; j < n; ++j) {
            vowels += vs[s.charCodeAt(j) - 'a'.charCodeAt(0)];
            const consonants = j - i + 1 - vowels;
            if (vowels === consonants && (vowels * consonants) % k === 0) {
                ++ans;
            }
        }
    }
    return ans;
}
