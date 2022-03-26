/**
 Do not return anything, modify A in-place instead.
 */
function merge(A: number[], m: number, B: number[], n: number): void {
    for (let i = n + m - 1; i >= 0; i--) {
        const x = A[m - 1] ?? -Infinity;
        const y = B[n - 1] ?? -Infinity;
        if (x > y) {
            A[i] = x;
            m--;
        } else {
            A[i] = y;
            n--;
        }
    }
}
