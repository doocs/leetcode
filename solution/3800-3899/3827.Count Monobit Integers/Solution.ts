function countMonobit(n: number): number {
    let ans = 1;
    for (let i = 1, x = 1; x <= n; ++i) {
        ++ans;
        x += 1 << i;
    }
    return ans;
}
