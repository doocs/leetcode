function primeSubOperation(nums: number[]): boolean {
    const p: number[] = [];
    for (let i = 2; i <= 1000; ++i) {
        let ok = true;
        for (const j of p) {
            if (i % j === 0) {
                ok = false;
                break;
            }
        }
        if (ok) {
            p.push(i);
        }
    }
    const search = (x: number): number => {
        let l = 0;
        let r = p.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (p[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const n = nums.length;
    for (let i = n - 2; i >= 0; --i) {
        if (nums[i] < nums[i + 1]) {
            continue;
        }
        const j = search(nums[i] - nums[i + 1]);
        if (j === p.length || p[j] >= nums[i]) {
            return false;
        }
        nums[i] -= p[j];
    }
    return true;
}
