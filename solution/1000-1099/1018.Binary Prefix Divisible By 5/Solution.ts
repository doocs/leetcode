function prefixesDivBy5(nums: number[]): boolean[] {
    const ans: boolean[] = [];
    let x = 0;
    for (const v of nums) {
        x = ((x << 1) | v) % 5;
        ans.push(x === 0);
    }
    return ans;
}
