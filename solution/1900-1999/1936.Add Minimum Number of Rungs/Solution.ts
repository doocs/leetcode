function addRungs(rungs: number[], dist: number): number {
    let ans = 0;
    let prev = 0;
    for (const x of rungs) {
        ans += ((x - prev - 1) / dist) | 0;
        prev = x;
    }
    return ans;
}
