var MyCalendar = function () {
    this.tm = new TreeMap();
};

/**
 * @param {number} startTime
 * @param {number} endTime
 * @return {boolean}
 */
MyCalendar.prototype.book = function (startTime, endTime) {
    const e = this.tm.higher(startTime);
    if (e && e[1] < endTime) {
        return false;
    }
    this.tm.set(endTime, startTime);
    return true;
};

var RBTreeNode = class {
    constructor(data) {
        this.data = data;
        this.left = this.right = this.parent = null;
        this.color = 0;
        this.count = 1;
    }
    sibling() {
        if (!this.parent) return null;
        return this.isOnLeft() ? this.parent.right : this.parent.left;
    }
    isOnLeft() {
        return this === this.parent.left;
    }
    hasRedChild() {
        return (
            Boolean(this.left && this.left.color === 0) ||
            Boolean(this.right && this.right.color === 0)
        );
    }
};
var RBTree = class {
    constructor(compare = (l, r) => (l < r ? -1 : l > r ? 1 : 0)) {
        this.root = null;
        this.lt = (l, r) => compare(l, r) < 0;
    }
    rotateLeft(pt) {
        const right = pt.right;
        pt.right = right.left;
        if (pt.right) pt.right.parent = pt;
        right.parent = pt.parent;
        if (!pt.parent) this.root = right;
        else if (pt === pt.parent.left) pt.parent.left = right;
        else pt.parent.right = right;
        right.left = pt;
        pt.parent = right;
    }
    rotateRight(pt) {
        const left = pt.left;
        pt.left = left.right;
        if (pt.left) pt.left.parent = pt;
        left.parent = pt.parent;
        if (!pt.parent) this.root = left;
        else if (pt === pt.parent.left) pt.parent.left = left;
        else pt.parent.right = left;
        left.right = pt;
        pt.parent = left;
    }
    swapColor(p1, p2) {
        const tmp = p1.color;
        p1.color = p2.color;
        p2.color = tmp;
    }
    swapData(p1, p2) {
        const tmp = p1.data;
        p1.data = p2.data;
        p2.data = tmp;
    }
    fixAfterInsert(pt) {
        var _a;
        let parent = null;
        let grandParent = null;
        while (
            pt !== this.root &&
            pt.color !== 1 &&
            ((_a = pt.parent) == null ? void 0 : _a.color) === 0
        ) {
            parent = pt.parent;
            grandParent = pt.parent.parent;
            if (parent === (grandParent == null ? void 0 : grandParent.left)) {
                const uncle = grandParent.right;
                if (uncle && uncle.color === 0) {
                    grandParent.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent;
                } else {
                    if (pt === parent.right) {
                        this.rotateLeft(parent);
                        pt = parent;
                        parent = pt.parent;
                    }
                    this.rotateRight(grandParent);
                    this.swapColor(parent, grandParent);
                    pt = parent;
                }
            } else {
                const uncle = grandParent.left;
                if (uncle != null && uncle.color === 0) {
                    grandParent.color = 0;
                    parent.color = 1;
                    uncle.color = 1;
                    pt = grandParent;
                } else {
                    if (pt === parent.left) {
                        this.rotateRight(parent);
                        pt = parent;
                        parent = pt.parent;
                    }
                    this.rotateLeft(grandParent);
                    this.swapColor(parent, grandParent);
                    pt = parent;
                }
            }
        }
        this.root.color = 1;
    }
    delete(val) {
        const node = this.find(val);
        if (!node) return false;
        node.count--;
        if (!node.count) this.deleteNode(node);
        return true;
    }
    deleteAll(val) {
        const node = this.find(val);
        if (!node) return false;
        this.deleteNode(node);
        return true;
    }
    deleteNode(v) {
        const u = BSTreplace(v);
        const uvBlack = (u === null || u.color === 1) && v.color === 1;
        const parent = v.parent;
        if (!u) {
            if (v === this.root) this.root = null;
            else {
                if (uvBlack) {
                    this.fixDoubleBlack(v);
                } else {
                    if (v.sibling()) {
                        v.sibling().color = 0;
                    }
                }
                if (v.isOnLeft()) parent.left = null;
                else parent.right = null;
            }
            return;
        }
        if (!v.left || !v.right) {
            if (v === this.root) {
                v.data = u.data;
                v.left = v.right = null;
            } else {
                if (v.isOnLeft()) parent.left = u;
                else parent.right = u;
                u.parent = parent;
                if (uvBlack) this.fixDoubleBlack(u);
                else u.color = 1;
            }
            return;
        }
        this.swapData(u, v);
        this.deleteNode(u);
        function BSTreplace(x) {
            var _a;
            if (x.left && x.right) return successor(x.right);
            if (!x.left && !x.right) return null;
            return (_a = x.left) != null ? _a : x.right;
        }
        function successor(x) {
            let temp = x;
            while (temp.left) temp = temp.left;
            return temp;
        }
    }
    fixDoubleBlack(x) {
        if (x === this.root) return;
        const sibling = x.sibling();
        const parent = x.parent;
        if (!sibling) {
            this.fixDoubleBlack(parent);
        } else {
            if (sibling.color === 0) {
                parent.color = 0;
                sibling.color = 1;
                if (sibling.isOnLeft()) this.rotateRight(parent);
                else this.rotateLeft(parent);
                this.fixDoubleBlack(x);
            } else {
                if (sibling.hasRedChild()) {
                    if (sibling.left && sibling.left.color === 0) {
                        if (sibling.isOnLeft()) {
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateRight(parent);
                        } else {
                            sibling.left.color = parent.color;
                            this.rotateRight(sibling);
                            this.rotateLeft(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            sibling.right.color = parent.color;
                            this.rotateLeft(sibling);
                            this.rotateRight(parent);
                        } else {
                            sibling.right.color = sibling.color;
                            sibling.color = parent.color;
                            this.rotateLeft(parent);
                        }
                    }
                    parent.color = 1;
                } else {
                    sibling.color = 0;
                    if (parent.color === 1) this.fixDoubleBlack(parent);
                    else parent.color = 1;
                }
            }
        }
    }
    insert(data) {
        let parent = this.root;
        while (parent) {
            if (this.lt(data, parent.data)) {
                if (!parent.left) break;
                else parent = parent.left;
            } else if (this.lt(parent.data, data)) {
                if (!parent.right) break;
                else parent = parent.right;
            } else break;
        }
        const node = new RBTreeNode(data);
        if (!parent) this.root = node;
        else if (this.lt(node.data, parent.data)) parent.left = node;
        else if (this.lt(parent.data, node.data)) parent.right = node;
        else {
            parent.count++;
            return false;
        }
        node.parent = parent;
        this.fixAfterInsert(node);
        return true;
    }
    search(predicate, direction) {
        let p = this.root;
        let result = null;
        while (p) {
            if (predicate(p.data)) {
                result = p;
                p = p[direction];
            } else {
                p = p[direction === 'left' ? 'right' : 'left'];
            }
        }
        return result == null ? void 0 : result.data;
    }
    find(data) {
        let p = this.root;
        while (p) {
            if (this.lt(data, p.data)) {
                p = p.left;
            } else if (this.lt(p.data, data)) {
                p = p.right;
            } else break;
        }
        return p != null ? p : null;
    }
    count(data) {
        const node = this.find(data);
        return node ? node.count : 0;
    }
    *inOrder(root = this.root) {
        if (!root) return;
        for (const v of this.inOrder(root.left)) yield v;
        yield root.data;
        for (const v of this.inOrder(root.right)) yield v;
    }
    *reverseInOrder(root = this.root) {
        if (!root) return;
        for (const v of this.reverseInOrder(root.right)) yield v;
        yield root.data;
        for (const v of this.reverseInOrder(root.left)) yield v;
    }
};

// src/treemap.ts
var TreeMap = class {
    constructor(collection = [], compare = (l, r) => (l < r ? -1 : l > r ? 1 : 0)) {
        this.map = new Map();
        if (typeof collection === 'function') {
            compare = collection;
            collection = [];
        }
        this._size = 0;
        this.compare = compare;
        this.tree = new RBTree(compare);
        for (const [key, val] of collection) this.set(key, val);
    }
    size() {
        return this._size;
    }
    has(key) {
        return !!this.tree.find(key);
    }
    get(key) {
        return this.map.get(key);
    }
    set(key, val) {
        const successful = this.tree.insert(key);
        this._size += successful ? 1 : 0;
        this.map.set(key, val);
        return successful;
    }
    delete(key) {
        const deleted = this.tree.deleteAll(key);
        this._size -= deleted ? 1 : 0;
        return deleted;
    }
    ceil(target) {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) >= 0, 'left'));
    }
    floor(target) {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) <= 0, 'right'));
    }
    higher(target) {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) > 0, 'left'));
    }
    lower(target) {
        return this.toKeyValue(this.tree.search(key => this.compare(key, target) < 0, 'right'));
    }
    first() {
        return this.toKeyValue(this.tree.inOrder().next().value);
    }
    last() {
        return this.toKeyValue(this.tree.reverseInOrder().next().value);
    }
    shift() {
        const first = this.first();
        if (first === void 0) return void 0;
        this.delete(first[0]);
        return first;
    }
    pop() {
        const last = this.last();
        if (last === void 0) return void 0;
        this.delete(last[0]);
        return last;
    }
    toKeyValue(key) {
        return key != null ? [key, this.map.get(key)] : void 0;
    }
    *[Symbol.iterator]() {
        for (const key of this.keys()) yield this.toKeyValue(key);
    }
    *keys() {
        for (const key of this.tree.inOrder()) yield key;
    }
    *values() {
        for (const key of this.keys()) yield this.map.get(key);
        return void 0;
    }
    *rkeys() {
        for (const key of this.tree.reverseInOrder()) yield key;
        return void 0;
    }
    *rvalues() {
        for (const key of this.rkeys()) yield this.map.get(key);
        return void 0;
    }
};

/**
 * Your MyCalendar object will be instantiated and called as such:
 * var obj = new MyCalendar()
 * var param_1 = obj.book(startTime,endTime)
 */
