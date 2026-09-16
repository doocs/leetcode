const mod = BigInt(1e9 + 7);

class Node {
    left: Node | null = null;
    right: Node | null = null;
    l: number;
    r: number;
    mid: number;
    v = 0n;
    add = 0n;
    mul = 1n;

    constructor(l: number, r: number) {
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
    }
}

class SegmentTree {
    root = new Node(1, 1e5 + 1);

    modify(l: number, r: number, mul: bigint, add: bigint, node = this.root): void {
        if (l > r) {
            return;
        }
        if (node.l >= l && node.r <= r) {
            this.apply(node, mul, add);
            return;
        }
        this.pushdown(node);
        if (l <= node.mid) {
            this.modify(l, r, mul, add, node.left!);
        }
        if (r > node.mid) {
            this.modify(l, r, mul, add, node.right!);
        }
        this.pushup(node);
    }

    query(l: number, r: number, node = this.root): bigint {
        if (l > r) {
            return 0n;
        }
        if (node.l >= l && node.r <= r) {
            return node.v;
        }
        this.pushdown(node);
        let v = 0n;
        if (l <= node.mid) {
            v = (v + this.query(l, r, node.left!)) % mod;
        }
        if (r > node.mid) {
            v = (v + this.query(l, r, node.right!)) % mod;
        }
        return v;
    }

    apply(node: Node, mul: bigint, add: bigint): void {
        node.v = (node.v * mul + BigInt(node.r - node.l + 1) * add) % mod;
        node.add = (node.add * mul + add) % mod;
        node.mul = (node.mul * mul) % mod;
    }

    pushup(node: Node): void {
        node.v = (node.left!.v + node.right!.v) % mod;
    }

    pushdown(node: Node): void {
        node.left ??= new Node(node.l, node.mid);
        node.right ??= new Node(node.mid + 1, node.r);
        if (node.add !== 0n || node.mul !== 1n) {
            this.apply(node.left, node.mul, node.add);
            this.apply(node.right, node.mul, node.add);
            node.add = 0n;
            node.mul = 1n;
        }
    }
}

class Fancy {
    private n = 0;
    private tree = new SegmentTree();

    append(val: number): void {
        this.n++;
        this.tree.modify(this.n, this.n, 1n, BigInt(val));
    }

    addAll(inc: number): void {
        this.tree.modify(1, this.n, 1n, BigInt(inc));
    }

    multAll(m: number): void {
        this.tree.modify(1, this.n, BigInt(m), 0n);
    }

    getIndex(idx: number): number {
        return idx >= this.n ? -1 : Number(this.tree.query(idx + 1, idx + 1));
    }
}
