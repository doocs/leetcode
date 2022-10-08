function advantageCount(nums1: number[], nums2: number[]): number[] {
    const n = nums1.length;
    const idx = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => nums2[i] - nums2[j]);
    nums1.sort((a, b) => a - b);

    const ans = new Array(n).fill(0);
    let left = 0;
    let right = n - 1;
    for (let i = 0; i < n; i++) {
        if (nums1[i] > nums2[idx[left]]) {
            ans[idx[left]] = nums1[i];
            left++;
        } else {
            ans[idx[right]] = nums1[i];
            right--;
        }
    }
    return ans;
}
