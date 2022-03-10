function sumOfUnique(nums: number[]): number {
    let res = 0;
    const map = new Map();
    for (const num of nums) {
        if (map.has(num)) {
            if (map.get(num)) {
                map.set(num, false);
                res -= num;
            }
        } else {
            map.set(num, true);
            res += num;
        }
    }
    return res;
}
