class KthLargest {
    #pq = new MinPriorityQueue();
    #k = 0;

    constructor(k: number, nums: number[]) {
        this.#k = k;
        for (const x of nums) {
            this.#pq.enqueue(x);
            if (this.#pq.size() > k) {
                this.#pq.dequeue();
            }
        }
    }

    add(val: number): number {
        this.#pq.enqueue(val);
        if (this.#pq.size() > this.#k) {
            this.#pq.dequeue();
        }
        return this.#pq.front().element;
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */
