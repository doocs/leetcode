function shareCandies(candies: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    for (const x of candies.slice(k)) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    let ans = cnt.size;
    for (let i = k; i < candies.length; ++i) {
        cnt.set(candies[i - k], (cnt.get(candies[i - k]) || 0) + 1);
        cnt.set(candies[i], (cnt.get(candies[i]) || 0) - 1);
        if (cnt.get(candies[i]) === 0) {
            cnt.delete(candies[i]);
        }
        ans = Math.max(ans, cnt.size);
    }
    return ans;
}
