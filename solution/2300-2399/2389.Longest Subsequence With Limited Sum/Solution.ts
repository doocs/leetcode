function answerQueries(nums: number[], queries: number[]): number[] {
    nums.sort((a, b) => a - b);
    for (let i = 1; i < nums.length; i++) {
        nums[i] += nums[i - 1];
    }
    const ans: number[] = [];
    const search = (nums: number[], x: number) => {
        let l = 0;
        let r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    for (const q of queries) {
        ans.push(search(nums, q));
    }
    return ans;
}
