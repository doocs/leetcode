function findDifferentBinaryString(nums: string[]): string {
    const n = nums.length;
    const ans: string[] = new Array(n);
    for (let i = 0; i < n; i++) {
        ans[i] = nums[i][i] === '0' ? '1' : '0';
    }
    return ans.join('');
}
