function countPairs(nums: number[], target: number): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    const search = (x: number, r: number): number => {
        let l = 0;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (let j = 0; j < nums.length; ++j) {
        const i = search(target - nums[j], j);
        ans += i;
    }
    return ans;
}
