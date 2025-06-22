function minSwaps(nums: number[]): number {
    const pos: number[][] = [[], []];
    for (let i = 0; i < nums.length; ++i) {
        pos[nums[i] & 1].push(i);
    }
    if (Math.abs(pos[0].length - pos[1].length) > 1) {
        return -1;
    }
    const calc = (k: number): number => {
        let res = 0;
        for (let i = 0; i < nums.length; i += 2) {
            res += Math.abs(pos[k][i >> 1] - i);
        }
        return res;
    };
    if (pos[0].length > pos[1].length) {
        return calc(0);
    }
    if (pos[0].length < pos[1].length) {
        return calc(1);
    }
    return Math.min(calc(0), calc(1));
}
