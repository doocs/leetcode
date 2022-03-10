function reversePairs(nums: number[]): number {
    let count: number = 0;
    const n: number = nums.length;
    if (n < 2) return 0;

    function merge(
        nums: number[],
        left: number,
        mid: number,
        right: number,
    ): void {
        let n: number = right - left + 1;
        let t: number[] = new Array(n);
        let i: number = left,
            j: number = mid + 1,
            idx: number = 0;
        while (i <= mid && j <= right) {
            if (nums[i] > nums[j]) {
                count += mid - i + 1;
                t[idx++] = nums[j++];
            } else {
                t[idx++] = nums[i++];
            }
        }
        while (i <= mid) {
            t[idx++] = nums[i++];
        }
        while (j <= right) {
            t[idx++] = nums[j++];
        }
        for (let k: number = 0; k < n; ++k) {
            nums[left + k] = t[k];
        }
    }

    function mergeSort(nums: number[], left: number, right: number): void {
        if (left == right) return;
        let mid: number = (left + right) >> 1;
        mergeSort(nums, left, mid);
        mergeSort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    mergeSort(nums, 0, n - 1);
    return count;
}
