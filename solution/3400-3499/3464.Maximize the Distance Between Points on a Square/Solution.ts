function maxDistance(side: number, points: number[][], k: number): number {
    const nums: number[] = [];
    for (const [x, y] of points) {
        if (x === 0) {
            nums.push(y);
        } else if (y === side) {
            nums.push(side + x);
        } else if (x === side) {
            nums.push(side * 3 - y);
        } else {
            nums.push(side * 4 - x);
        }
    }
    nums.sort((a, b) => a - b);

    const check = (lo: number): boolean => {
        const total = side * 4;
        for (const start of nums) {
            const end = start + total - lo;
            let cur = start;
            let ok = true;
            for (let i = 0; i < k - 1; i++) {
                const j = _.sortedIndex(nums, cur + lo);
                if (j === nums.length || nums[j] > end) {
                    ok = false;
                    break;
                }
                cur = nums[j];
            }
            if (ok) {
                return true;
            }
        }
        return false;
    };

    let l = 1,
        r = side;
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
