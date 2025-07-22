function calculateScore(s: string): number {
    const d: Map<string, number[]> = new Map();
    const n = s.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const x = s[i];
        const y = String.fromCharCode('a'.charCodeAt(0) + 'z'.charCodeAt(0) - x.charCodeAt(0));

        if (d.has(y)) {
            const ls = d.get(y)!;
            const j = ls.pop()!;
            if (ls.length === 0) {
                d.delete(y);
            }
            ans += i - j;
        } else {
            if (!d.has(x)) {
                d.set(x, []);
            }
            d.get(x)!.push(i);
        }
    }
    return ans;
}
