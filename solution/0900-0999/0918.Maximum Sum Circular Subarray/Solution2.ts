function maxSubarraySumCircular(nums: number[]): number {
    const inf = 1 << 30;
    let [pmi, pmx] = [0, -inf];
    let [ans, s, smi] = [-inf, 0, inf];
    for (const x of nums) {
        s += x;
        ans = Math.max(ans, s - pmi);
        smi = Math.min(smi, s - pmx);
        pmi = Math.min(pmi, s);
        pmx = Math.max(pmx, s);
    }
    return Math.max(ans, s - smi);
}
