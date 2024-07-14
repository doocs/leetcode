/**
 Do not return anything, modify A in-place instead.
 */
function merge(A: number[], m: number, B: number[], n: number): void {
    let [i, j] = [m - 1, n - 1];
    for (let k = A.length - 1; ~k; --k) {
        if (j < 0 || (i >= 0 && A[i] > B[j])) {
            A[k] = A[i--];
        } else {
            A[k] = B[j--];
        }
    }
}
