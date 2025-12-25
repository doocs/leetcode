class KthLargest {
    #k: number = 0;
    #minQ = new MinPriorityQueue<number>();

    constructor(k: number, nums: number[]) {
        this.#k = k;
        for (const x of nums) {
            this.add(x);
        }
    }

    add(val: number): number {
        this.#minQ.enqueue(val);
        if (this.#minQ.size() > this.#k) {
            this.#minQ.dequeue();
        }
        return this.#minQ.front();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */
