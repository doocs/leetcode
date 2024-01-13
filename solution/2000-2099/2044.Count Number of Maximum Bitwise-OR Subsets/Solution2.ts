function countMaxOrSubsets(nums: number[]): number {
    const n = nums.length;
    let res = 0;
    let max = -Infinity;
    const dfs = (i: number, sum: number) => {
        for (let j = i; j < n; j++) {
            const num = sum | nums[j];
            if (num >= max) {
                if (num > max) {
                    max = num;
                    res = 0;
                }
                res++;
            }
            dfs(j + 1, num);
        }
    };
    dfs(0, 0);

    return res;
}
