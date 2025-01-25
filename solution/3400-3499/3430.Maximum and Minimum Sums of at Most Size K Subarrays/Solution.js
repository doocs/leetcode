/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var minMaxSubarraySum = function(nums, k) {
    const computeSum = (nums, k, isMin) => {
        const n = nums.length;
        const prev = new Array(n).fill(-1);
        const next = new Array(n).fill(n);
        let stack = [];
        
        if (isMin) {
            for (let i = 0; i < n; i++) {
                while (stack.length > 0 && nums[stack[stack.length - 1]] >= nums[i]) {
                    stack.pop();
                }
                prev[i] = stack.length > 0 ? stack[stack.length - 1] : -1;
                stack.push(i);
            }
            stack = [];
            for (let i = n - 1; i >= 0; i--) {
                while (stack.length > 0 && nums[stack[stack.length - 1]] > nums[i]) {
                    stack.pop();
                }
                next[i] = stack.length > 0 ? stack[stack.length - 1] : n;
                stack.push(i);
            }
        } else {
            for (let i = 0; i < n; i++) {
                while (stack.length > 0 && nums[stack[stack.length - 1]] <= nums[i]) {
                    stack.pop();
                }
                prev[i] = stack.length > 0 ? stack[stack.length - 1] : -1;
                stack.push(i);
            }
            stack = [];
            for (let i = n - 1; i >= 0; i--) {
                while (stack.length > 0 && nums[stack[stack.length - 1]] < nums[i]) {
                    stack.pop();
                }
                next[i] = stack.length > 0 ? stack[stack.length - 1] : n;
                stack.push(i);
            }
        }

        let sum = 0;
        for (let i = 0; i < n; i++) {
            const left = prev[i];
            const right = next[i];
            const a = left + 1;
            const b = i;
            const c = i;
            const d = right - 1;

            let s_start = Math.max(a, i - k + 1);
            let s_end_candidate = d - k + 1;
            let s_upper = Math.min(b, s_end_candidate);

            let sum1 = 0;
            if (s_upper >= s_start) {
                const num_terms = s_upper - s_start + 1;
                const first = s_start;
                const last = s_upper;
                const sum_s = (last * (last + 1)) / 2 - ((first - 1) * first) / 2;
                const sum_k = (k - i) * num_terms;
                sum1 = sum_s + sum_k;
            }

            let s2_start = s_upper + 1;
            let s2_end = b;
            s2_start = Math.max(s2_start, a);
            s2_end = Math.min(s2_end, b);

            let sum2 = 0;
            if (s2_start <= s2_end) {
                const count = s2_end - s2_start + 1;
                const term = d - i + 1;
                sum2 = term * count;
            }

            sum += nums[i] * (sum1 + sum2);
        }
        return sum;
    };

    const sumMin = computeSum(nums, k, true);
    const sumMax = computeSum(nums, k, false);
    return sumMin + sumMax;
};
