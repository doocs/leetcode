class Node {
    constructor(key = '', cnt = 0) {
        this.prev = null;
        this.next = null;
        this.cnt = cnt;
        this.keys = new Set([key]);
    }

    insert(node) {
        node.prev = this;
        node.next = this.next;
        this.next.prev = node;
        this.next = node;
        return node;
    }

    remove() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
    }
}

var AllOne = function() {
    this.root = new Node();
    this.root.next = this.root;
    this.root.prev = this.root;
    this.nodes = {};
};

AllOne.prototype.inc = function(key) {
    const root = this.root;
    const nodes = this.nodes;

    if (!nodes[key]) {
        if (root.next === root || root.next.cnt > 1) {
            nodes[key] = root.insert(new Node(key, 1));
        } else {
            root.next.keys.add(key);
            nodes[key] = root.next;
        }
    } else {
        const curr = nodes[key];
        const next = curr.next;

        if (next === root || next.cnt > curr.cnt + 1) {
            nodes[key] = curr.insert(new Node(key, curr.cnt + 1));
        } else {
            next.keys.add(key);
            nodes[key] = next;
        }

        curr.keys.delete(key);
        if (curr.keys.size === 0) {
            curr.remove();
        }
    }
};

AllOne.prototype.dec = function(key) {
    const root = this.root;
    const nodes = this.nodes;
    const curr = nodes[key];

    if (curr.cnt === 1) {
        delete nodes[key];
    } else {
        const prev = curr.prev;

        if (prev === root || prev.cnt < curr.cnt - 1) {
            nodes[key] = prev.insert(new Node(key, curr.cnt - 1));
        } else {
            prev.keys.add(key);
            nodes[key] = prev;
        }
    }

    curr.keys.delete(key);
    if (curr.keys.size === 0) {
        curr.remove();
    }
};

AllOne.prototype.getMaxKey = function() {
    return this.root.prev === this.root ? '' : Array.from(this.root.prev.keys)[0];
};

AllOne.prototype.getMinKey = function() {
    return this.root.next === this.root ? '' : Array.from(this.root.next.keys)[0];
};

/** 
 * Your AllOne object will be instantiated and called as such:
 * var obj = new AllOne()
 * obj.inc(key)
 * obj.dec(key)
 * var param_3 = obj.getMaxKey()
 * var param_4 = obj.getMinKey()
 */
