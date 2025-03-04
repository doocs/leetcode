function findDifferentBinaryString(nums: string[]): string {
    const set = new Set(nums.map(x => Number.parseInt(x, 2)));
    let res = 0;

    while (set.has(res)) {
        res++;
    }

    return res.toString(2).padStart(nums[0].length, '0');
}
