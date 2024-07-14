function divisorSubstrings(num: number, k: number): number {
    let [x, p, t] = [0, 1, num];
    for (; k > 0; k--) {
        const v = t % 10;
        t = Math.floor(t / 10);
        x = p * v + x;
        p *= 10;
    }
    let ans = x !== 0 && num % x === 0 ? 1 : 0;
    for (p = Math.floor(p / 10); t > 0; t = Math.floor(t / 10)) {
        x = Math.floor(x / 10);
        x = p * (t % 10) + x;
        ans += x !== 0 && num % x === 0 ? 1 : 0;
    }
    return ans;
}
