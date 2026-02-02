function separateSquares(squares: number[][]): number {
    const check = (y1: number): boolean => {
        let t = 0;
        for (const [_, y, l] of squares) {
            if (y < y1) {
                t += l * Math.min(y1 - y, l);
            }
        }
        return t >= s / 2;
    };

    let s = 0;
    let l = 0;
    let r = 0;
    for (const a of squares) {
        s += a[2] * a[2];
        r = Math.max(r, a[1] + a[2]);
    }

    const eps = 1e-5;
    while (r - l > eps) {
        const mid = (l + r) / 2;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid;
        }
    }
    return r;
}
