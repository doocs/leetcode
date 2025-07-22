function smallestNumber(n: number, t: number): number {
    for (let i = n; ; ++i) {
        let p = 1;
        for (let x = i; x; x = Math.floor(x / 10)) {
            p *= x % 10;
        }
        if (p % t === 0) {
            return i;
        }
    }
}
