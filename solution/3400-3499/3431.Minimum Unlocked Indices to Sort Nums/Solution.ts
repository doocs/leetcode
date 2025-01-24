function minUnlockedIndices(nums: number[], locked: number[]): number {
    const n = nums.length;
    let [first2, first3] = [n, n];
    let [last1, last2] = [-1, -1];

    for (let i = 0; i < n; i++) {
        if (nums[i] === 1) {
            last1 = i;
        } else if (nums[i] === 2) {
            first2 = Math.min(first2, i);
            last2 = i;
        } else {
            first3 = Math.min(first3, i);
        }
    }

    if (first3 < last1) {
        return -1;
    }

    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (locked[i] === 1 && ((first2 <= i && i < last1) || (first3 <= i && i < last2))) {
            ans++;
        }
    }

    return ans;
}
