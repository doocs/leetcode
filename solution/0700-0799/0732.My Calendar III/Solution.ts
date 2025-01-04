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

    constructor() {}

    modify(l: number, r: number, v: number, node: Node = this.root): void {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v += v;
            node.add += v;
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modify(l, r, v, node.left!);
        }
        if (r > node.mid) {
            this.modify(l, r, v, node.right!);
        }
        this.pushup(node);
    }

    query(l: number, r: number, node: Node = this.root): number {
        if (l > r) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0;
        if (l <= node.mid) {
            v = Math.max(v, this.query(l, r, node.left!));
        }
        if (r > node.mid) {
            v = Math.max(v, this.query(l, r, node.right!));
        }
        return v;
    }

    private pushup(node: Node): void {
        node.v = Math.max(node.left!.v, node.right!.v);
    }

    private pushdown(node: Node): void {
        if (node.left === null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right === null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add !== 0) {
            const left = node.left!;
            const right = node.right!;
            left.add += node.add;
            right.add += node.add;
            left.v += node.add;
            right.v += node.add;
            node.add = 0;
        }
    }
}

class MyCalendarThree {
    private tree: SegmentTree;

    constructor() {
        this.tree = new SegmentTree();
    }

    book(start: number, end: number): number {
        this.tree.modify(start + 1, end, 1);
        return this.tree.query(1, 1e9 + 1);
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * var obj = new MyCalendarThree()
 * var param_1 = obj.book(startTime, endTime)
 */
