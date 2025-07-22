function numTriplets(nums1: number[], nums2: number[]): number {
    const cnt1 = count(nums1);
    const cnt2 = count(nums2);
    return cal(cnt1, nums2) + cal(cnt2, nums1);
}

function count(nums: number[]): Map<number, number> {
    const cnt: Map<number, number> = new Map();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) || 0) + 1);
    }
    return cnt;
}

function cal(cnt: Map<number, number>, nums: number[]): number {
    let ans: number = 0;
    for (const x of nums) {
        for (const [y, v1] of cnt) {
            const z = Math.floor((x * x) / y);
            if (y * z == x * x) {
                const v2 = cnt.get(z) || 0;
                ans += v1 * (y === z ? v2 - 1 : v2);
            }
        }
    }
    return ans / 2;
}
