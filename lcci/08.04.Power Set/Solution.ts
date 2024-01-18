function subsets(nums: number[]): number[][] {
    const res = [[]];
    nums.forEach(num => {
        res.forEach(item => {
            res.push(item.concat(num));
        });
    });
    return res;
}
