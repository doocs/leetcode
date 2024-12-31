class Node {
    key: number;
    val: number;
    prev: Node | null;
    next: Node | null;

    constructor(key: number, val: number) {
        this.key = key;
        this.val = val;
        this.prev = null;
        this.next = null;
    }
}

class LRUCache {
    private size: number;
    private capacity: number;
    private head: Node;
    private tail: Node;
    private cache: Map<number, Node>;

    constructor(capacity: number) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.cache = new Map();
    }

    get(key: number): number {
        if (!this.cache.has(key)) {
            return -1;
        }
        const node = this.cache.get(key)!;
        this.removeNode(node);
        this.addToHead(node);
        return node.val;
    }

    put(key: number, value: number): void {
        if (this.cache.has(key)) {
            const node = this.cache.get(key)!;
            this.removeNode(node);
            node.val = value;
            this.addToHead(node);
        } else {
            const node = new Node(key, value);
            this.cache.set(key, node);
            this.addToHead(node);
            if (++this.size > this.capacity) {
                const nodeToRemove = this.tail.prev!;
                this.cache.delete(nodeToRemove.key);
                this.removeNode(nodeToRemove);
                --this.size;
            }
        }
    }

    private removeNode(node: Node): void {
        if (!node) return;
        node.prev!.next = node.next;
        node.next!.prev = node.prev;
    }

    private addToHead(node: Node): void {
        node.next = this.head.next;
        node.prev = this.head;
        this.head.next!.prev = node;
        this.head.next = node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * var obj = new LRUCache(capacity)
 * var param_1 = obj.get(key)
 * obj.put(key,value)
 */
