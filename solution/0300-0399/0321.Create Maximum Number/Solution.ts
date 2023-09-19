function maxNumber(nums1: number[], nums2: number[], k: number): number[] {
    const m = nums1.length;
    const n = nums2.length;
    const l = Math.max(0, k - n);
    const r = Math.min(k, m);
    let ans: number[] = Array(k).fill(0);
    for (let x = l; x <= r; ++x) {
        const arr1 = f(nums1, x);
        const arr2 = f(nums2, k - x);
        const arr = merge(arr1, arr2);
        if (compare(arr, ans, 0, 0)) {
            ans = arr;
        }
    }
    return ans;
}

function f(nums: number[], k: number): number[] {
    const n = nums.length;
    const stk: number[] = Array(k).fill(0);
    let top = -1;
    let remain = n - k;
    for (const x of nums) {
        while (top >= 0 && stk[top] < x && remain > 0) {
            --top;
            --remain;
        }
        if (top + 1 < k) {
            stk[++top] = x;
        } else {
            --remain;
        }
    }
    return stk;
}

function compare(nums1: number[], nums2: number[], i: number, j: number): boolean {
    if (i >= nums1.length) {
        return false;
    }
    if (j >= nums2.length) {
        return true;
    }
    if (nums1[i] > nums2[j]) {
        return true;
    }
    if (nums1[i] < nums2[j]) {
        return false;
    }
    return compare(nums1, nums2, i + 1, j + 1);
}

function merge(nums1: number[], nums2: number[]): number[] {
    const m = nums1.length;
    const n = nums2.length;
    const ans: number[] = Array(m + n).fill(0);
    let i = 0;
    let j = 0;
    for (let k = 0; k < m + n; ++k) {
        if (compare(nums1, nums2, i, j)) {
            ans[k] = nums1[i++];
        } else {
            ans[k] = nums2[j++];
        }
    }
    return ans;
}
