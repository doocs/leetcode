function minTimeToType(word: string): number {
    let a = 'a'.charCodeAt(0);
    let ans = word.length;
    for (const c of word) {
        const d = Math.abs(c.charCodeAt(0) - a);
        ans += Math.min(d, 26 - d);
        a = c.charCodeAt(0);
    }
    return ans;
}
