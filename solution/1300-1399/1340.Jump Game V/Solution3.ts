function maxJumps(arr: number[], d: number): number {
    const n = arr.length;
    const f: number[] = new Array(n).fill(1);
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((a, b) => arr[a] - arr[b]);
    for (const i of idx) {
        for (let j = i - 1; j >= 0; j--) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            f[i] = Math.max(f[i], 1 + f[j]);
        }
        for (let j = i + 1; j < n; j++) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            f[i] = Math.max(f[i], 1 + f[j]);
        }
    }
    return Math.max(...f);
}
