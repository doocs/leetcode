function mostFrequentEven(nums: number[]): number {
    const map = new Map();
    for (const num of nums) {
        if (num % 2 === 0) {
            map.set(num, (map.get(num) ?? 0) + 1);
        }
    }
    if (map.size === 0) {
        return -1;
    }

    let res = 0;
    let max = 0;
    for (const [k, v] of map.entries()) {
        if (v > max || (v == max && k < res)) {
            max = v;
            res = k;
        }
    }
    return res;
}
