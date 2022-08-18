function maxEqualFreq(nums: number[]): number {
    const n = nums.length;
    const map = new Map();
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }

    for (let i = n - 1; i > 0; i--) {
        for (const k of map.keys()) {
            map.set(k, map.get(k) - 1);
            let num = 0;
            for (const v of map.values()) {
                if (v !== 0) {
                    num = v;
                    break;
                }
            }
            let isOk = true;
            let sum = 1;
            for (const v of map.values()) {
                if (v !== 0 && v !== num) {
                    isOk = false;
                    break;
                }
                sum += v;
            }
            if (isOk) {
                return sum;
            }
            map.set(k, map.get(k) + 1);
        }
        map.set(nums[i], map.get(nums[i]) - 1);
    }
    return 1;
}
