class Node {
    left: Node | null;
    right: Node | null;
    add: number;
    v: boolean;

    constructor() {
        this.left = null;
        this.right = null;
        this.add = 0;
        this.v = false;
    }
}

class SegmentTree {
    private root: Node;

    constructor() {
        this.root = new Node();
    }

    modify(
        left: number,
        right: number,
        v: number,
        l: number = 1,
        r: number = 1e9,
        node: Node | null = null,
    ): void {
        if (node === null) {
            node = this.root;
        }

        if (l >= left && r <= right) {
            node.v = v === 1;
            node.add = v;
            return;
        }

        this.pushdown(node);

        const mid = (l + r) >> 1;

        if (left <= mid) {
            this.modify(left, right, v, l, mid, node.left);
        }

        if (right > mid) {
            this.modify(left, right, v, mid + 1, r, node.right);
        }

        this.pushup(node);
    }

    query(
        left: number,
        right: number,
        l: number = 1,
        r: number = 1e9,
        node: Node | null = null,
    ): boolean {
        if (node === null) {
            node = this.root;
        }

        if (l >= left && r <= right) {
            return node.v;
        }

        this.pushdown(node);

        const mid = (l + r) >> 1;
        let result = true;

        if (left <= mid) {
            result = result && this.query(left, right, l, mid, node.left);
        }

        if (right > mid) {
            result = result && this.query(left, right, mid + 1, r, node.right);
        }

        return result;
    }

    pushup(node: Node): void {
        node.v = !!(node.left && node.left.v && node.right && node.right.v);
    }

    pushdown(node: Node): void {
        if (node.left === null) {
            node.left = new Node();
        }

        if (node.right === null) {
            node.right = new Node();
        }

        if (node.add !== 0) {
            node.left.add = node.add;
            node.right.add = node.add;
            node.left.v = node.add === 1;
            node.right.v = node.add === 1;
            node.add = 0;
        }
    }
}

class RangeModule {
    private tree: SegmentTree;

    constructor() {
        this.tree = new SegmentTree();
    }

    addRange(left: number, right: number): void {
        this.tree.modify(left, right - 1, 1);
    }

    queryRange(left: number, right: number): boolean {
        return this.tree.query(left, right - 1);
    }

    removeRange(left: number, right: number): void {
        this.tree.modify(left, right - 1, -1);
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * var obj = new RangeModule()
 * obj.addRange(left,right)
 * var param_2 = obj.queryRange(left,right)
 * obj.removeRange(left,right)
 */
