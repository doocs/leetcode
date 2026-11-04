class Node {
    key: number;
    pri: number;
    cnt: number;
    left: Node | null = null;
    right: Node | null = null;

    constructor(key: number) {
        this.key = key;
        this.pri = Math.random();
        this.cnt = 1;
    }
}

class Treap {
    root: Node | null = null;

    rotateRight(o: Node): Node {
        const p = o.left!;
        o.left = p.right;
        p.right = o;
        return p;
    }

    rotateLeft(o: Node): Node {
        const p = o.right!;
        o.right = p.left;
        p.left = o;
        return p;
    }

    merge(a: Node | null, b: Node | null): Node | null {
        if (!a) return b;
        if (!b) return a;
        if (a.pri > b.pri) {
            a.right = this.merge(a.right, b);
            return a;
        }
        b.left = this.merge(a, b.left);
        return b;
    }

    insert(o: Node | null, x: number): Node {
        if (!o) return new Node(x);

        if (x === o.key) {
            o.cnt++;
        } else if (x < o.key) {
            o.left = this.insert(o.left, x);
            if (o.left.pri > o.pri) {
                o = this.rotateRight(o);
            }
        } else {
            o.right = this.insert(o.right, x);
            if (o.right.pri > o.pri) {
                o = this.rotateLeft(o);
            }
        }
        return o;
    }

    erase(o: Node | null, x: number): Node | null {
        if (!o) return null;

        if (x < o.key) {
            o.left = this.erase(o.left, x);
        } else if (x > o.key) {
            o.right = this.erase(o.right, x);
        } else {
            if (o.cnt > 1) {
                o.cnt--;
            } else {
                return this.merge(o.left, o.right);
            }
        }
        return o;
    }

    add(x: number): void {
        this.root = this.insert(this.root, x);
    }

    remove(x: number): void {
        this.root = this.erase(this.root, x);
    }

    contains(x: number): boolean {
        let o = this.root;
        while (o) {
            if (x === o.key) return true;
            o = x < o.key ? o.left : o.right;
        }
        return false;
    }

    first(): number {
        let o = this.root!;
        while (o.left) o = o.left;
        return o.key;
    }

    last(): number {
        let o = this.root!;
        while (o.right) o = o.right;
        return o.key;
    }
}

class MedianCost {
    l = new Treap();
    r = new Treap();
    n1 = 0;
    n2 = 0;
    s1 = 0;
    s2 = 0;

    adjust(): void {
        while (this.n1 - this.n2 > 1) {
            const x = this.l.last();
            this.l.remove(x);
            this.n1--;
            this.s1 -= x;

            this.r.add(x);
            this.n2++;
            this.s2 += x;
        }

        while (this.n2 > this.n1) {
            const x = this.r.first();
            this.r.remove(x);
            this.n2--;
            this.s2 -= x;

            this.l.add(x);
            this.n1++;
            this.s1 += x;
        }
    }

    add(x: number): void {
        this.r.add(x);
        this.n2++;
        this.s2 += x;

        const y = this.r.first();
        this.r.remove(y);
        this.n2--;
        this.s2 -= y;

        this.l.add(y);
        this.n1++;
        this.s1 += y;

        this.adjust();
    }

    remove(x: number): void {
        if (this.l.contains(x)) {
            this.l.remove(x);
            this.n1--;
            this.s1 -= x;
        } else {
            this.r.remove(x);
            this.n2--;
            this.s2 -= x;
        }
        this.adjust();
    }

    query(): number {
        const mid = this.l.last();
        return this.n1 * mid - this.s1 + this.s2 - this.n2 * mid;
    }
}

function minOperations(nums: number[], x: number, k: number): number {
    const n = nums.length;
    const INF = Number.MAX_SAFE_INTEGER;

    const f: number[][] = Array.from({ length: n + 1 }, () => new Array(k + 1).fill(INF));
    f[0][0] = 0;

    const mc = new MedianCost();

    for (let i = 1; i <= n; i++) {
        mc.add(nums[i - 1]);
        const w = mc.query();

        for (let j = 0; j <= k; j++) {
            f[i][j] = f[i - 1][j];
            if (j > 0 && i >= x) {
                f[i][j] = Math.min(f[i][j], f[i - x][j - 1] + w);
            }
        }

        if (i >= x) {
            mc.remove(nums[i - x]);
        }
    }

    return f[n][k];
}
