const MX = 100001;
const g: number[][] = Array.from({ length: MX }, () => []);
for (let i = 1; i < MX; i++) {
    for (let j = i; j < MX; j += i) {
        g[j].push(i);
    }
}

function minDifference(n: number, k: number): number[] {
    let cur = Number.MAX_SAFE_INTEGER;
    let ans: number[] | null = null;
    const path: number[] = Array(k).fill(0);

    function dfs(i: number, x: number, mi: number, mx: number): void {
        if (i === 0) {
            const d = Math.max(mx, x) - Math.min(mi, x);
            if (d < cur) {
                cur = d;
                path[i] = x;
                ans = [...path];
            }
            return;
        }
        for (const y of g[x]) {
            path[i] = y;
            dfs(i - 1, Math.floor(x / y), Math.min(mi, y), Math.max(mx, y));
        }
    }

    dfs(k - 1, n, Number.MAX_SAFE_INTEGER, 0);
    return ans ?? [];
}
