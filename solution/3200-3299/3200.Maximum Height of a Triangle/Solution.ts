function maxHeightOfTriangle(red: number, blue: number): number {
    let ans = 0;
    for (let k = 0; k < 2; ++k) {
        const c: [number, number] = [red, blue];
        for (let i = 1, j = k; i <= c[j]; ++i, j ^= 1) {
            c[j] -= i;
            ans = Math.max(ans, i);
        }
    }
    return ans;
}
