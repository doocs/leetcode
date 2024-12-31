/**
 * @param {number} capacity
 */
var LRUCache = function (capacity) {
    this.size = 0;
    this.capacity = capacity;
    this.cache = new Map();
    this.head = new Node(0, 0);
    this.tail = new Node(0, 0);
    this.head.next = this.tail;
    this.tail.prev = this.head;
};

/**
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function (key) {
    if (!this.cache.has(key)) {
        return -1;
    }
    const node = this.cache.get(key);
    this.removeNode(node);
    this.addToHead(node);
    return node.val;
};

/**
 * @param {number} key
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function (key, value) {
    if (this.cache.has(key)) {
        const node = this.cache.get(key);
        this.removeNode(node);
        node.val = value;
        this.addToHead(node);
    } else {
        const node = new Node(key, value);
        this.cache.set(key, node);
        this.addToHead(node);
        if (++this.size > this.capacity) {
            const nodeToRemove = this.tail.prev;
            this.cache.delete(nodeToRemove.key);
            this.removeNode(nodeToRemove);
            --this.size;
        }
    }
};

LRUCache.prototype.removeNode = function (node) {
    if (!node) return;
    node.prev.next = node.next;
    node.next.prev = node.prev;
};

LRUCache.prototype.addToHead = function (node) {
    node.next = this.head.next;
    node.prev = this.head;
    this.head.next.prev = node;
    this.head.next = node;
};

/**
 * @constructor
 * @param {number} key
 * @param {number} val
 */
function Node(key, val) {
    this.key = key;
    this.val = val;
    this.prev = null;
    this.next = null;
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
