function weightedSum(parent: number[], nums: number[]): number {
    const n = nums.length;
    const g: number[][] = Array.from({ length: n }, () => []);
    for (let i = 1; i < n; i++) {
        g[parent[i]].push(i);
    }
    let ans = 0n;
    let q: number[] = [0];
    let d = 0;
    while (q.length > 0) {
        d++;
        const nq: number[] = [];
        for (const i of q) {
            ans += BigInt(nums[i]) * BigInt(1 - d);
            for (const son of g[i]) {
                nq.push(son);
            }
        }
        q = nq;
    }
    let sum = 0n;
    for (const x of nums) {
        sum += BigInt(x);
    }
    ans += BigInt(d) * sum;
    return Number(ans);
}
