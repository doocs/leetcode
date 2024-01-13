function countEven(num: number): number {
    let ans = 0;
    for (let i = 1; i <= num; ++i) {
        let s = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            s += x % 10;
        }
        if (s % 2 == 0) {
            ++ans;
        }
    }
    return ans;
}
