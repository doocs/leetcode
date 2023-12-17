function divideArray(nums: number[], k: number): number[][] {
    nums.sort((a, b) => a - b);
    const ans: number[][] = [];
    for (let i = 0; i < nums.length; i += 3) {
        const t = nums.slice(i, i + 3);
        if (t[2] - t[0] > k) {
            return [];
        }
        ans.push(t);
    }
    return ans;
}
