function canAliceWin(n: number): boolean {
    let [x, k] = [10, 0];
    while (n >= x) {
        n -= x;
        --x;
        ++k;
    }
    return k % 2 === 1;
}
