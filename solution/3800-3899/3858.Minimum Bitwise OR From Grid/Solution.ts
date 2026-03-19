function minimumOR(grid: number[][]): number {
    let mx = 0;
    for (const row of grid) {
        mx = Math.max(mx, Math.max(...row));
    }

    const m = mx === 0 ? 0 : 32 - Math.clz32(mx);
    let ans = 0;

    for (let i = m - 1; i >= 0; i--) {
        const mask = ans | ((1 << i) - 1);
        for (const row of grid) {
            let found = false;
            for (const x of row) {
                if ((x | mask) === mask) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                ans |= 1 << i;
                break;
            }
        }
    }

    return ans;
}
