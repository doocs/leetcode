class Solution {
    private nums: number[];

    constructor(nums: number[]) {
        this.nums = nums;
    }

    reset(): number[] {
        return this.nums;
    }

    shuffle(): number[] {
        const n = this.nums.length;
        const res = [...this.nums];
        for (let i = 0; i < n; i++) {
            const j = Math.floor(Math.random() * n);
            [res[i], res[j]] = [res[j], res[i]];
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = new Solution(nums)
 * var param_1 = obj.reset()
 * var param_2 = obj.shuffle()
 */
