function subtractProductAndSum(n: number): number {
    let p = 1;
    let s = 0;
    while (n) {
        const num = n % 10;
        n = Math.floor(n / 10);
        p *= num;
        s += num;
    }
    return p - s;
}
