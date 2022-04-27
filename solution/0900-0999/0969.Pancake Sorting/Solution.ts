function pancakeSort(arr: number[]): number[] {
    let ans = [];
    for (let n = arr.length; n > 1; n--) {
        let index = 0;
        for (let i = 1; i < n; i++) {
            if (arr[i] >= arr[index]) {
                index = i;
            }
        }
        if (index == n - 1) continue;
        reverse(arr, index);
        reverse(arr, n - 1);
        ans.push(index + 1);
        ans.push(n);
    }
    return ans;
}

function reverse(nums: Array<number>, end: number): void {
    for (let i = 0, j = end; i < j; i++, j--) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
}
