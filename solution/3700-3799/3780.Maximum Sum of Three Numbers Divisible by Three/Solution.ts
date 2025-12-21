function maximumSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const g: number[][] = Array.from({ length: 3 }, () => []);
    for (const x of nums) {
        g[x % 3].push(x);
    }
    let ans = 0;
    for (let a = 0; a < 3; a++) {
        if (g[a].length > 0) {
            const x = g[a].pop()!;
            for (let b = 0; b < 3; b++) {
                if (g[b].length > 0) {
                    const y = g[b].pop()!;
                    const c = (3 - ((a + b) % 3)) % 3;
                    if (g[c].length > 0) {
                        const z = g[c][g[c].length - 1];
                        ans = Math.max(ans, x + y + z);
                    }
                    g[b].push(y);
                }
            }
            g[a].push(x);
        }
    }
    return ans;
}
