function missingInteger(nums: number[]): number {
    let s = nums[0];
    for (let j = 1; j < nums.length && nums[j] === nums[j - 1] + 1; ++j) {
        s += nums[j];
    }

    const m = 51;
    const st = new Array<boolean>(m).fill(false);
    for (const x of nums) {
        st[x] = true;
    }

    while (s < m && st[s]) {
        ++s;
    }
    return s;
}
