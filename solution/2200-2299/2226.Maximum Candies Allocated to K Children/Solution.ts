function maximumCandies(candies: number[], k: number): number {
    let [l, r] = [0, Math.max(...candies)];
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        const cnt = candies.reduce((acc, cur) => acc + Math.floor(cur / mid), 0);
        if (cnt >= k) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
