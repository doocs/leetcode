/**
 Do not return anything, modify C in-place instead.
 */
function hanota(A: number[], B: number[], C: number[]): void {
    const stk: any[] = [[A.length, A, B, C]];
    while (stk.length) {
        const [n, a, b, c] = stk.pop()!;
        if (n === 1) {
            c.push(a.pop());
        } else {
            stk.push([n - 1, b, a, c]);
            stk.push([1, a, b, c]);
            stk.push([n - 1, a, c, b]);
        }
    }
}
