class SparseVector {
    d: Map<number, number>;

    constructor(nums: number[]) {
        this.d = new Map();
        for (let i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                this.d.set(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    dotProduct(vec: SparseVector): number {
        let a = this.d;
        let b = vec.d;
        if (a.size > b.size) {
            [a, b] = [b, a];
        }
        let ans = 0;
        for (const [i, x] of a) {
            if (b.has(i)) {
                ans += x * b.get(i)!;
            }
        }
        return ans;
    }
}

/**
 * Your SparseVector object will be instantiated and called as such:
 * var v1 = new SparseVector(nums1)
 * var v2 = new SparseVector(nums1)
 * var ans = v1.dotProduct(v2)
 */
