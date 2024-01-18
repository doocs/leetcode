function maximizeSquareArea(m: number, n: number, hFences: number[], vFences: number[]): number {
    const f = (nums: number[], k: number): Set<number> => {
        nums.push(1, k);
        nums.sort((a, b) => a - b);
        const s: Set<number> = new Set();
        for (let i = 0; i < nums.length; ++i) {
            for (let j = 0; j < i; ++j) {
                s.add(nums[i] - nums[j]);
            }
        }
        return s;
    };
    const hs = f(hFences, m);
    const vs = f(vFences, n);
    let ans = 0;
    for (const h of hs) {
        if (vs.has(h)) {
            ans = Math.max(ans, h);
        }
    }
    return ans ? Number(BigInt(ans) ** 2n % 1000000007n) : -1;
}
