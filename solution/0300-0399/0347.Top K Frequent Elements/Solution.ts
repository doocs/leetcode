function topKFrequent(nums: number[], k: number): number[] {
    const cnt = new Map();
    for (const num of nums) {
        cnt.set(num, (cnt.get(num) || 0) + 1);
    }

    return [...cnt]
        .sort((a, b) => b[1] - a[1])
        .splice(0, k)
        .map(([x]) => x);
}
