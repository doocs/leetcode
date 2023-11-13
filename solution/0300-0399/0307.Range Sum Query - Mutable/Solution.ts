class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        while (x <= this.n) {
            this.c[x] += delta;
            x += x & -x;
        }
    }

    query(x: number): number {
        let s = 0;
        while (x > 0) {
            s += this.c[x];
            x -= x & -x;
        }
        return s;
    }
}

class NumArray {
    private tree: BinaryIndexedTree;

    constructor(nums: number[]) {
        const n = nums.length;
        this.tree = new BinaryIndexedTree(n);
        for (let i = 0; i < n; ++i) {
            this.tree.update(i + 1, nums[i]);
        }
    }

    update(index: number, val: number): void {
        const prev = this.sumRange(index, index);
        this.tree.update(index + 1, val - prev);
    }

    sumRange(left: number, right: number): number {
        return this.tree.query(right + 1) - this.tree.query(left);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * obj.update(index,val)
 * var param_2 = obj.sumRange(left,right)
 */
