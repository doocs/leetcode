const MOD = 1000000007n;

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
    root: Node;

    constructor() {
        this.root = new Node(1, 100001);
    }

    modifyAdd(l: number, r: number, inc: bigint, node: Node = this.root): void {
        if (l > r) return;

        if (node.l >= l && node.r <= r) {
            node.v = (node.v + BigInt(node.r - node.l + 1) * inc) % MOD;
            node.add = (node.add + inc) % MOD;
            return;
        }

        this.pushdown(node);

        if (l <= node.mid) this.modifyAdd(l, r, inc, node.left!);
        if (r > node.mid) this.modifyAdd(l, r, inc, node.right!);

        this.pushup(node);
    }

    modifyMul(l: number, r: number, m: bigint, node: Node = this.root): void {
        if (l > r) return;

        if (node.l >= l && node.r <= r) {
            node.v = (node.v * m) % MOD;
            node.add = (node.add * m) % MOD;
            node.mul = (node.mul * m) % MOD;
            return;
        }

        this.pushdown(node);

        if (l <= node.mid) this.modifyMul(l, r, m, node.left!);
        if (r > node.mid) this.modifyMul(l, r, m, node.right!);

        this.pushup(node);
    }

    query(l: number, r: number, node: Node = this.root): bigint {
        if (l > r) return 0n;

        if (node.l >= l && node.r <= r) return node.v;

        this.pushdown(node);

        let v = 0n;

        if (l <= node.mid) v = (v + this.query(l, r, node.left!)) % MOD;
        if (r > node.mid) v = (v + this.query(l, r, node.right!)) % MOD;

        return v;
    }

    pushup(node: Node): void {
        node.v = (node.left!.v + node.right!.v) % MOD;
    }

    pushdown(node: Node): void {
        if (!node.left) node.left = new Node(node.l, node.mid);
        if (!node.right) node.right = new Node(node.mid + 1, node.r);

        if (node.add !== 0n || node.mul !== 1n) {
            const add = node.add;
            const mul = node.mul;

            const left = node.left!;
            const right = node.right!;

            left.v = (left.v * mul + BigInt(left.r - left.l + 1) * add) % MOD;
            right.v = (right.v * mul + BigInt(right.r - right.l + 1) * add) % MOD;

            left.add = (left.add * mul + add) % MOD;
            right.add = (right.add * mul + add) % MOD;

            left.mul = (left.mul * mul) % MOD;
            right.mul = (right.mul * mul) % MOD;

            node.add = 0n;
            node.mul = 1n;
        }
    }
}

class Fancy {
    n = 0;
    tree = new SegmentTree();

    append(val: number): void {
        this.n++;
        this.tree.modifyAdd(this.n, this.n, BigInt(val));
    }

    addAll(inc: number): void {
        this.tree.modifyAdd(1, this.n, BigInt(inc));
    }

    multAll(m: number): void {
        this.tree.modifyMul(1, this.n, BigInt(m));
    }

    getIndex(idx: number): number {
        if (idx >= this.n) return -1;
        return Number(this.tree.query(idx + 1, idx + 1));
    }
}

/**
 * Your Fancy object will be instantiated and called as such:
 * var obj = new Fancy()
 * obj.append(val)
 * obj.addAll(inc)
 * obj.multAll(m)
 * var param_4 = obj.getIndex(idx)
 */
