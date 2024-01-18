function getMinSwaps(num: string, k: number): number {
    const n = num.length;
    const s = num.split('');
    for (let i = 0; i < k; ++i) {
        nextPermutation(s);
    }
    const d: number[][] = Array.from({ length: 10 }, () => []);
    for (let i = 0; i < n; ++i) {
        d[+num[i]].push(i);
    }
    const idx: number[] = Array(10).fill(0);
    const arr: number[] = [];
    for (let i = 0; i < n; ++i) {
        arr.push(d[+s[i]][idx[+s[i]]++]);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < i; ++j) {
            if (arr[j] > arr[i]) {
                ans++;
            }
        }
    }
    return ans;
}

function nextPermutation(nums: string[]): boolean {
    const n = nums.length;
    let i = n - 2;
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }
    if (i < 0) {
        return false;
    }
    let j = n - 1;
    while (j >= 0 && nums[i] >= nums[j]) {
        j--;
    }
    [nums[i], nums[j]] = [nums[j], nums[i]];
    for (i = i + 1, j = n - 1; i < j; ++i, --j) {
        [nums[i], nums[j]] = [nums[j], nums[i]];
    }
    return true;
}
