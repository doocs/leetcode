class Node {
    constructor(l, r) {
        this.left = null;
        this.right = null;
        this.l = l;
        this.r = r;
        this.mid = (l + r) >> 1;
        this.v = 0;
        this.add = 0;
    }
}

class SegmentTree {
    constructor() {
        this.root = new Node(1, 1e9 + 1);
    }

    modify(l, r, v, node = this.root) {
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

    query(l, r, node = this.root) {
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

    pushup(node) {
        if (node.left && node.right) {
            node.v = Math.max(node.left.v, node.right.v);
        }
    }

    pushdown(node) {
        if (!node.left) {
            node.left = new Node(node.l, node.mid);
        }
        if (!node.right) {
            node.right = new Node(node.mid + 1, node.r);
        }
        if (node.add) {
            const left = node.left;
            const right = node.right;
            left.add += node.add;
            right.add += node.add;
            left.v += node.add;
            right.v += node.add;
            node.add = 0;
        }
    }
}

var MyCalendarTwo = function () {
    this.tree = new SegmentTree();
};

/**
 * @param {number} startTime
 * @param {number} endTime
 * @return {boolean}
 */
MyCalendarTwo.prototype.book = function (startTime, endTime) {
    if (this.tree.query(startTime + 1, endTime) >= 2) {
        return false;
    }
    this.tree.modify(startTime + 1, endTime, 1);
    return true;
};

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * var obj = new MyCalendarTwo()
 * var param_1 = obj.book(startTime,endTime)
 */
