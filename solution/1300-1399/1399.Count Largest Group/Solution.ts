function countLargestGroup(n: number): number {
    const cnt: number[] = new Array(40).fill(0);
    let mx = 0;
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        let s = 0;
        for (let x = i; x; x = Math.floor(x / 10)) {
            s += x % 10;
        }
        ++cnt[s];
        if (mx < cnt[s]) {
            mx = cnt[s];
            ans = 1;
        } else if (mx === cnt[s]) {
            ++ans;
        }
    }
    return ans;
}
