function truncateSentence(s: string, k: number): string {
    for (let i = 0; i < s.length; ++i) {
        if (s[i] === ' ' && --k === 0) {
            return s.slice(0, i);
        }
    }
    return s;
}
