function countSubarrays(nums: number[], minK: number, maxK: number): number {
    let res = 0;
    let minIndex = -1;
    let maxIndex = -1;
    let k = -1;
    nums.forEach((num, i) => {
        if (num === minK) {
            minIndex = i;
        }
        if (num === maxK) {
            maxIndex = i;
        }
        if (num < minK || num > maxK) {
            k = i;
        }
        res += Math.max(Math.min(minIndex, maxIndex) - k, 0);
    });
    return res;
}
