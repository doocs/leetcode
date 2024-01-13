function topKFrequent(nums: number[], k: number): number[] {
    const map = new Map<number, number>();
    let maxCount = 0;
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
        maxCount = Math.max(maxCount, map.get(num));
    }

    const res = [];
    while (k > 0) {
        for (const key of map.keys()) {
            if (map.get(key) === maxCount) {
                res.push(key);
                k--;
            }
        }
        maxCount--;
    }
    return res;
}
