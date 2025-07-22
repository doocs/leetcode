function crackSafe(n: number, k: number): string {
    function dfs(u: number): void {
        for (let x = 0; x < k; x++) {
            const e = u * 10 + x;
            if (!vis.has(e)) {
                vis.add(e);
                const v = e % mod;
                dfs(v);
                ans.push(x.toString());
            }
        }
    }

    const mod = Math.pow(10, n - 1);
    const vis = new Set<number>();
    const ans: string[] = [];

    dfs(0);
    ans.push('0'.repeat(n - 1));
    return ans.join('');
}
