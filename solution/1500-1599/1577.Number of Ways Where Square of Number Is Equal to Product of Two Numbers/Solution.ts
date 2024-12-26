function numTriplets(nums1: number[], nums2: number[]): number {
    const cnt1 = count(nums1);
    const cnt2 = count(nums2);
    return cal(cnt1, nums2) + cal(cnt2, nums1);
}

function count(nums: number[]): Map<number, number> {
    const cnt: Map<number, number> = new Map();
    for (let j = 0; j < nums.length; ++j) {
        for (let k = j + 1; k < nums.length; ++k) {
            const x = nums[j] * nums[k];
            cnt.set(x, (cnt.get(x) || 0) + 1);
        }
    }
    return cnt;
}

function cal(cnt: Map<number, number>, nums: number[]): number {
    return nums.reduce((acc, x) => acc + (cnt.get(x * x) || 0), 0);
}
