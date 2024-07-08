function subsets(nums: number[]): number[][] {
    const res: number[][] = [[]];
    for (const x of nums) {
        res.push(...res.map(arr => [...arr, x]));
    }

    return res;
}
