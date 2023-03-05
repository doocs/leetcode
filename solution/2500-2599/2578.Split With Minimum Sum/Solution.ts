function splitNum(num: number): number {
    const cnt = new Array(10).fill(0);
    let n = 0;
    for (; num > 0; num = Math.floor(num / 10)) {
        ++cnt[num % 10];
        ++n;
    }
    const ans = new Array(2).fill(0);
    for (let i = 0, j = 0; i < n; ++i) {
        while (cnt[j] === 0) {
            ++j;
        }
        --cnt[j];
        ans[i & 1] = ans[i & 1] * 10 + j;
    }
    return ans[0] + ans[1];
}
