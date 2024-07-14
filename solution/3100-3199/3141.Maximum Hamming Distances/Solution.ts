function maxHammingDistances(nums: number[], m: number): number[] {
    const dist: number[] = Array.from({ length: 1 << m }, () => -1);
    const q: number[] = [];
    for (const x of nums) {
        dist[x] = 0;
        q.push(x);
    }
    for (let k = 1; q.length; ++k) {
        const t: number[] = [];
        for (const x of q) {
            for (let i = 0; i < m; ++i) {
                const y = x ^ (1 << i);
                if (dist[y] === -1) {
                    dist[y] = k;
                    t.push(y);
                }
            }
        }
        q.splice(0, q.length, ...t);
    }
    for (let i = 0; i < nums.length; ++i) {
        nums[i] = m - dist[nums[i] ^ ((1 << m) - 1)];
    }
    return nums;
}
