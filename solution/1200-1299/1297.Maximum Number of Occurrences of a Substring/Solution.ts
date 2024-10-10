function maxFreq(s: string, maxLetters: number, minSize: number, maxSize: number): number {
    const cnt = new Map<string, number>();
    let ans = 0;
    for (let i = 0; i < s.length - minSize + 1; ++i) {
        const t = s.slice(i, i + minSize);
        const ss = new Set(t.split(''));
        if (ss.size <= maxLetters) {
            cnt.set(t, (cnt.get(t) || 0) + 1);
            ans = Math.max(ans, cnt.get(t)!);
        }
    }
    return ans;
}
