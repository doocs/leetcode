class Node {
    l: number;
    r: number;
    v: number;
}

class SegmentTree {
    private tr: Node[];
    private nums: number[];

    constructor(nums: number[]) {
        this.nums = nums;
        const n = nums.length;
        this.tr = new Array<Node>(n << 2);
        for (let i = 0; i < this.tr.length; ++i) {
            this.tr[i] = { l: 0, r: 0, v: 0 };
        }
        this.build(1, 1, n);
    }

    build(u: number, l: number, r: number): void {
        this.tr[u].l = l;
        this.tr[u].r = r;
        if (l == r) {
            this.tr[u].v = this.nums[l - 1];
            return;
        }
        const mid = (l + r) >> 1;
        this.build(u << 1, l, mid);
        this.build((u << 1) | 1, mid + 1, r);
        this.pushup(u);
    }

    modify(u: number, x: number, v: number): void {
        if (this.tr[u].l == x && this.tr[u].r == x) {
            this.tr[u].v = v;
            return;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        if (x <= mid) {
            this.modify(u << 1, x, v);
        } else {
            this.modify((u << 1) | 1, x, v);
        }
        this.pushup(u);
    }

    query(u: number, l: number, r: number): number {
        if (this.tr[u].l >= l && this.tr[u].r <= r) {
            return this.tr[u].v;
        }
        const mid = (this.tr[u].l + this.tr[u].r) >> 1;
        let v = 0;
        if (l <= mid) {
            v += this.query(u << 1, l, r);
        }
        if (r > mid) {
            v += this.query((u << 1) | 1, l, r);
        }
        return v;
    }

    pushup(u: number): void {
        this.tr[u].v = this.tr[u << 1].v + this.tr[(u << 1) | 1].v;
    }
}

class NumArray {
    private tree: SegmentTree;

    constructor(nums: number[]) {
        this.tree = new SegmentTree(nums);
    }

    update(index: number, val: number): void {
        this.tree.modify(1, index + 1, val);
    }

    sumRange(left: number, right: number): number {
        return this.tree.query(1, left + 1, right + 1);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * var obj = new NumArray(nums)
 * obj.update(index,val)
 * var param_2 = obj.sumRange(left,right)
 */
