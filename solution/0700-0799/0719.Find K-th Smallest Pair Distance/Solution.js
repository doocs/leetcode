/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
function smallestDistancePair(nums, k) {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let left = 0,
        right = nums[n - 1] - nums[0];

    while (left < right) {
        const mid = (left + right) >> 1;
        let count = 0,
            i = 0;

        for (let j = 0; j < n; j++) {
            while (nums[j] - nums[i] > mid) {
                i++;
            }
            count += j - i;
        }

        if (count >= k) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }

    return left;
}
