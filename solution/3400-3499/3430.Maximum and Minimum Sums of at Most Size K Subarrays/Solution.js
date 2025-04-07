/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var minMaxSubarraySum = function (nums, k) {
    const computeSum = (nums, k, isMin) => {
        const n = nums.length;
        const prev = Array(n).fill(-1);
        const next = Array(n).fill(n);
        let stk = [];

        if (isMin) {
            for (let i = 0; i < n; i++) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] >= nums[i]) {
                    stk.pop();
                }
                prev[i] = stk.length > 0 ? stk[stk.length - 1] : -1;
                stk.push(i);
            }
            stk = [];
            for (let i = n - 1; i >= 0; i--) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] > nums[i]) {
                    stk.pop();
                }
                next[i] = stk.length > 0 ? stk[stk.length - 1] : n;
                stk.push(i);
            }
        } else {
            for (let i = 0; i < n; i++) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] <= nums[i]) {
                    stk.pop();
                }
                prev[i] = stk.length > 0 ? stk[stk.length - 1] : -1;
                stk.push(i);
            }
            stk = [];
            for (let i = n - 1; i >= 0; i--) {
                while (stk.length > 0 && nums[stk[stk.length - 1]] < nums[i]) {
                    stk.pop();
                }
                next[i] = stk.length > 0 ? stk[stk.length - 1] : n;
                stk.push(i);
            }
        }

        let totalSum = 0;
        for (let i = 0; i < n; i++) {
            const left = prev[i];
            const right = next[i];
            const a = left + 1;
            const b = i;
            const c = i;
            const d = right - 1;

            let start1 = Math.max(a, i - k + 1);
            let endCandidate1 = d - k + 1;
            let upper1 = Math.min(b, endCandidate1);

            let sum1 = 0;
            if (upper1 >= start1) {
                const termCount = upper1 - start1 + 1;
                const first = start1;
                const last = upper1;
                const indexSum = (last * (last + 1)) / 2 - ((first - 1) * first) / 2;
                const constantSum = (k - i) * termCount;
                sum1 = indexSum + constantSum;
            }

            let start2 = upper1 + 1;
            let end2 = b;
            start2 = Math.max(start2, a);
            end2 = Math.min(end2, b);

            let sum2 = 0;
            if (start2 <= end2) {
                const count = end2 - start2 + 1;
                const term = d - i + 1;
                sum2 = term * count;
            }

            totalSum += nums[i] * (sum1 + sum2);
        }

        return totalSum;
    };

    const minSum = computeSum(nums, k, true);
    const maxSum = computeSum(nums, k, false);
    return minSum + maxSum;
};
