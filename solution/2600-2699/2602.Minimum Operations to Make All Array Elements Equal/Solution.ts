function minOperations(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    const s: number[] = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        s[i + 1] = s[i] + nums[i];
    }
    const search = (x: number): number => {
        let l = 0;
        let r = n;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const ans: number[] = [];
    for (const x of queries) {
        const i = search(x + 1);
        let t = s[n] - s[i] - (n - i) * x;
        const j = search(x);
        t += x * j - s[j];
        ans.push(t);
    }
    return ans;
}
