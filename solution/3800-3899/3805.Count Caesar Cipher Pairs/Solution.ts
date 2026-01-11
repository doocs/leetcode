function countPairs(words: string[]): number {
    const cnt = new Map<string, number>();
    let ans = 0;
    for (const s of words) {
        const t = s.split('');
        const k = 'z'.charCodeAt(0) - t[0].charCodeAt(0);
        for (let i = 1; i < t.length; i++) {
            t[i] = String.fromCharCode(97 + ((t[i].charCodeAt(0) - 97 + k) % 26));
        }
        t[0] = 'z';
        const key = t.join('');
        cnt.set(key, (cnt.get(key) || 0) + 1);
    }
    for (const v of cnt.values()) {
        ans += (v * (v - 1)) / 2;
    }
    return ans;
}
