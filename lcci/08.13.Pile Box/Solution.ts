function pileBox(box: number[][]): number {
    box.sort((a, b) => (a[0] === b[0] ? b[1] - a[1] : a[0] - b[0]));
    const n = box.length;
    const f: number[] = new Array(n).fill(0);
    let ans: number = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (box[j][1] < box[i][1] && box[j][2] < box[i][2]) {
                f[i] = Math.max(f[i], f[j]);
            }
        }
        f[i] += box[i][2];
        ans = Math.max(ans, f[i]);
    }
    return ans;
}
