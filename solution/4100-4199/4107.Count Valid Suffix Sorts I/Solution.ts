function countSuffix(nums: number[], target: number[]): number {
    const diff = new Map<number, number>();
    for (const x of nums) {
        diff.set(x, (diff.get(x) ?? 0) + 1);
    }
    for (const x of target) {
        const cnt = (diff.get(x) ?? 0) - 1;
        diff.set(x, cnt);
        if (cnt < 0) {
            return 0;
        }
    }

    const n = nums.length;
    let maxI = n - 1;
    for (let i = 0; i < n; i++) {
        if (nums[i] !== target[i]) {
            maxI = i;
            break;
        }
    }

    let minI = 0;
    for (let i = n - 1; i > 0; i--) {
        if (target[i - 1] < target[i]) {
            minI = i;
            break;
        }
    }

    return Math.max(maxI - minI + 1, 0);
}
