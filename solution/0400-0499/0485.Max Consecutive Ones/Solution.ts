function findMaxConsecutiveOnes(nums: number[]): number {
    let res = 0;
    let count = 0;
    for (const num of nums) {
        if (num === 0) {
            res = Math.max(res, count);
            count = 0;
        } else {
            count++;
        }
    }
    return Math.max(res, count);
}
