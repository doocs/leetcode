class Node {
    left: Node | null = null;
    right: Node | null = null;
    l: number;
    r: number;
    mid: number;
    v: number = 0;
    add: number = 0;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    private root: Node = new Node(1, 1e9 + 1);

    modify(l: number, r: number, v: number, node: Node | null = this.root): void {
        if (l > r || !node) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v += v;
            node.add += v;
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modify(l, r, v, node.left);
        }
        if (r > node.mid) {
            this.modify(l, r, v, node.right);
        }
        this.pushup(node);
    }

    query(l: number, r: number, node: Node | null = this.root): number {
        if (l > r || !node) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0;
        if (l <= node.mid) {
            v = Math.max(v, this.query(l, r, node.left));
        }
        if (r > node.mid) {
            v = Math.max(v, this.query(l, r, node.right));
        }
        return v;
    }

    private pushup(node: Node): void {
        if (node.left && node.right) {
            node.v = Math.max(node.left.v, node.right.v);
        }
    }

    private pushdown(node: Node): void {
        if (!node.left) {
            node.left = new Node(node.l, node.mid);
        }
        if (!node.right) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add) {
            let left = node.left;
            let right = node.right;
            left.add += node.add;
            right.add += node.add;
            left.v += node.add;
            right.v += node.add;
            node.add = 0;
        }
    }
}

class MyCalendarTwo {
    private tree: SegmentTree = new SegmentTree();

    constructor() {}

    book(startTime: number, endTime: number): boolean {
        if (this.tree.query(startTime + 1, endTime) >= 2) {
            return false;
        }
        this.tree.modify(startTime + 1, endTime, 1);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
