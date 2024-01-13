function passThePillow(n: number, time: number): number {
    let ans = 1,
        k = 1;
    while (time-- > 0) {
        ans += k;
        if (ans === 1 || ans === n) {
            k *= -1;
        }
    }
    return ans;
}
