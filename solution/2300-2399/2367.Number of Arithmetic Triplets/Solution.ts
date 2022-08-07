function arithmeticTriplets(nums: number[], diff: number): number {
    let vis = new Array(310).fill(false);
    for (const v of nums) {
        vis[v] = true;
    }
    let ans = 0;
    for (const v of nums) {
        if (vis[v + diff] && vis[v + diff + diff]) {
            ++ans;
        }
    }
    return ans;
}
