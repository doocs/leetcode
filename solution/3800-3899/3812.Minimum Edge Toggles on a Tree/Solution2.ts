function minimumFlips(n: number, edges: number[][], start: string, target: string): number[] {
    const g: number[][][] = Array.from({ length: n }, () => []);
    for (let i = 0; i < n - 1; i++) {
        const [a, b] = edges[i];
        g[a].push([b, i]);
        g[b].push([a, i]);
    }
    const ans: number[] = [];
    const need: boolean[] = Array(n).fill(false);
    const stk: number[][] = [[0, -1, 0]];
    while (stk.length) {
        const [a, fa, state] = stk.pop()!;
        if (state === 0) {
            stk.push([a, fa, 1]);
            for (const [b] of g[a]) {
                if (b !== fa) {
                    stk.push([b, a, 0]);
                }
            }
        } else {
            let rev = start[a] !== target[a];
            for (const [b, i] of g[a]) {
                if (b !== fa && need[b]) {
                    ans.push(i);
                    rev = !rev;
                }
            }
            need[a] = rev;
        }
    }
    if (need[0]) {
        return [-1];
    }
    ans.sort((x, y) => x - y);
    return ans;
}
