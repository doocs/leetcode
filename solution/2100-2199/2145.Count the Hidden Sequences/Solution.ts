function numberOfArrays(differences: number[], lower: number, upper: number): number {
    let [x, mi, mx] = [0, 0, 0];
    for (const d of differences) {
        x += d;
        mi = Math.min(mi, x);
        mx = Math.max(mx, x);
    }
    return Math.max(0, upper - lower - (mx - mi) + 1);
}
