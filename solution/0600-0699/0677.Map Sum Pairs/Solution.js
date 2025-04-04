class Trie {
    constructor() {
        this.children = new Array(26);
        this.val = 0;
    }

    insert(w, x) {
        let node = this;
        for (const c of w) {
            const i = c.charCodeAt(0) - 97;
            if (!node.children[i]) {
                node.children[i] = new Trie();
            }
            node = node.children[i];
            node.val += x;
        }
    }

    search(w) {
        let node = this;
        for (const c of w) {
            const i = c.charCodeAt(0) - 97;
            if (!node.children[i]) {
                return 0;
            }
            node = node.children[i];
        }
        return node.val;
    }
}

var MapSum = function () {
    this.d = new Map();
    this.t = new Trie();
};

/**
 * @param {string} key
 * @param {number} val
 * @return {void}
 */
MapSum.prototype.insert = function (key, val) {
    const x = val - (this.d.get(key) ?? 0);
    this.d.set(key, val);
    this.t.insert(key, x);
};

/**
 * @param {string} prefix
 * @return {number}
 */
MapSum.prototype.sum = function (prefix) {
    return this.t.search(prefix);
};

/**
 * Your MapSum object will be instantiated and called as such:
 * var obj = new MapSum()
 * obj.insert(key,val)
 * var param_2 = obj.sum(prefix)
 */
