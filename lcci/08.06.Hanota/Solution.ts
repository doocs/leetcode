/**
 Do not return anything, modify C in-place instead.
 */
function hanota(A: number[], B: number[], C: number[]): void {
    const dfs = (n: number, a: number[], b: number[], c: number[]) => {
        if (n === 1) {
            c.push(a.pop()!);
            return;
        }
        dfs(n - 1, a, c, b);
        c.push(a.pop()!);
        dfs(n - 1, b, a, c);
    };
    dfs(A.length, A, B, C);
}
