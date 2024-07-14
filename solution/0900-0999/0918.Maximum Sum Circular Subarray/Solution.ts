function maxSubarraySumCircular(nums: number[]): number {
    let [pmi, pmx] = [0, -Infinity];
    let [ans, s, smi] = [-Infinity, 0, Infinity];
    for (const x of nums) {
        s += x;
        ans = Math.max(ans, s - pmi);
        smi = Math.min(smi, s - pmx);
        pmi = Math.min(pmi, s);
        pmx = Math.max(pmx, s);
    }
    return Math.max(ans, s - smi);
}
