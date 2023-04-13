function countTheNumOfKFreeSubsets(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const g: Map<number, number[]> = new Map();
    for (const x of nums) {
        const y = x % k;
        if (!g.has(y)) {
            g.set(y, []);
        }
        g.get(y)!.push(x);
    }
    let ans: number = 1;
    for (const [_, arr] of g) {
        const m = arr.length;
        const f: number[] = new Array(m + 1).fill(1);
        f[1] = 2;
        for (let i = 2; i <= m; ++i) {
            if (arr[i - 1] - arr[i - 2] === k) {
                f[i] = f[i - 1] + f[i - 2];
            } else {
                f[i] = f[i - 1] * 2;
            }
        }
        ans *= f[m];
    }
    return ans;
}
