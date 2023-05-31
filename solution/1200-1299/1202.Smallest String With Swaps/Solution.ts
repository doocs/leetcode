function smallestStringWithSwaps(s: string, pairs: number[][]): string {
    const n = s.length;
    const p = new Array(n).fill(0).map((_, i) => i);
    const find = (x: number): number => {
        if (p[x] !== x) {
            p[x] = find(p[x]);
        }
        return p[x];
    };
    const d: string[][] = new Array(n).fill(0).map(() => []);
    for (const [a, b] of pairs) {
        p[find(a)] = find(b);
    }
    for (let i = 0; i < n; ++i) {
        d[find(i)].push(s[i]);
    }
    for (const e of d) {
        e.sort((a, b) => b.charCodeAt(0) - a.charCodeAt(0));
    }
    const ans: string[] = [];
    for (let i = 0; i < n; ++i) {
        ans.push(d[find(i)].pop()!);
    }
    return ans.join('');
}
