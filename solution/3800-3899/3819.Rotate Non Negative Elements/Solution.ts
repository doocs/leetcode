function rotateElements(nums: number[], k: number): number[] {
    const t: number[] = nums.filter(x => x >= 0);
    const m = t.length;
    const d = new Array<number>(m);
    for (let i = 0; i < m; i++) {
        d[(((i - k) % m) + m) % m] = t[i];
    }
    let j = 0;
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] >= 0) {
            nums[i] = d[j++];
        }
    }
    return nums;
}
