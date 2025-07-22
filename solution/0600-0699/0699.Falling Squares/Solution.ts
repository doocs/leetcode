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
    private root: Node = new Node(1, 1e9);

    public modify(l: number, r: number, v: number): void {
        this.modifyNode(l, r, v, this.root);
    }

    private modifyNode(l: number, r: number, v: number, node: Node): void {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            node.v = v;
            node.add = v;
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modifyNode(l, r, v, node.left!);
        }
        if (r > node.mid) {
            this.modifyNode(l, r, v, node.right!);
        }
        this.pushup(node);
    }

    public query(l: number, r: number): number {
        return this.queryNode(l, r, this.root);
    }

    private queryNode(l: number, r: number, node: Node): number {
        if (l > r) {
            return 0;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0;
        if (l <= node.mid) {
            v = Math.max(v, this.queryNode(l, r, node.left!));
        }
        if (r > node.mid) {
            v = Math.max(v, this.queryNode(l, r, node.right!));
        }
        return v;
    }

    private pushup(node: Node): void {
        node.v = Math.max(node.left!.v, node.right!.v);
    }

    private pushdown(node: Node): void {
        if (node.left == null) {
            node.left = new Node(node.l, node.mid);
        }
        if (node.right == null) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add != 0) {
            let left = node.left,
                right = node.right;
            left!.add = node.add;
            right!.add = node.add;
            left!.v = node.add;
            right!.v = node.add;
            node.add = 0;
        }
    }
}

function fallingSquares(positions: number[][]): number[] {
    const ans: number[] = [];
    const tree = new SegmentTree();
    let mx = 0;
    for (const [l, w] of positions) {
        const r = l + w - 1;
        const h = tree.query(l, r) + w;
        mx = Math.max(mx, h);
        ans.push(mx);
        tree.modify(l, r, h);
    }
    return ans;
}
