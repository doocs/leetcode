function countPalindromePaths(parent: number[], s: string): number {
    const n = parent.length;
    const g: [number, number][][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; ++i) {
        g[parent[i]].push([i, 1 << (s.charCodeAt(i) - 97)]);
    }
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    let ans = 0;
    const stk: [number, number][] = [[0, 0]];
    while (stk.length) {
        const [i, xor] = stk.pop()!;
        for (const [j, v] of g[i]) {
            const x = xor ^ v;
            ans += cnt.get(x) || 0;
            for (let k = 0; k < 26; ++k) {
                ans += cnt.get(x ^ (1 << k)) || 0;
            }
            cnt.set(x, (cnt.get(x) || 0) + 1);
            stk.push([j, x]);
        }
    }
    return ans;
}
