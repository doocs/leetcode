function myPow(x: number, n: number): number {
    let res = 1;
    if (n < 0) {
        n = -n;
        x = 1 / x;
    }
    for (let i = n; i != 0; i = Math.floor(i / 2)) {
        if ((i & 1) == 1) {
            res *= x;
        }
        x *= x;
    }
    return res;
}
