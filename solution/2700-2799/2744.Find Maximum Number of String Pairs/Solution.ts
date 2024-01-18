function maximumNumberOfStringPairs(words: string[]): number {
    const cnt: { [key: number]: number } = {};
    let ans = 0;
    for (const w of words) {
        const [a, b] = [w.charCodeAt(0) - 97, w.charCodeAt(w.length - 1) - 97];
        ans += cnt[(b << 5) | a] || 0;
        cnt[(a << 5) | b] = (cnt[(a << 5) | b] || 0) + 1;
    }
    return ans;
}
