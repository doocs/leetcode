function countEven(num: number): number {
    let ans = Math.floor(num / 10) * 5 - 1;
    let s = 0;
    for (let x = Math.floor(num / 10); x; x = Math.floor(x / 10)) {
        s += x % 10;
    }
    ans += ((num % 10) + 2 - (s & 1)) >> 1;
    return ans;
}
