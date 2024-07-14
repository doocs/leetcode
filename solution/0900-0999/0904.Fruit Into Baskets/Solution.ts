function totalFruit(fruits: number[]): number {
    const n = fruits.length;
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, j = 0; i < n; ++i) {
        cnt.set(fruits[i], (cnt.get(fruits[i]) || 0) + 1);
        for (; cnt.size > 2; ++j) {
            cnt.set(fruits[j], cnt.get(fruits[j])! - 1);
            if (!cnt.get(fruits[j])) {
                cnt.delete(fruits[j]);
            }
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
