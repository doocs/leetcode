class RandomizedSet {
    private d: Map<number, number> = new Map();
    private q: number[] = [];

    constructor() {}

    insert(val: number): boolean {
        if (this.d.has(val)) {
            return false;
        }
        this.d.set(val, this.q.length);
        this.q.push(val);
        return true;
    }

    remove(val: number): boolean {
        if (!this.d.has(val)) {
            return false;
        }
        const i = this.d.get(val)!;
        this.d.set(this.q[this.q.length - 1], i);
        this.q[i] = this.q[this.q.length - 1];
        this.q.pop();
        this.d.delete(val);
        return true;
    }

    getRandom(): number {
        return this.q[Math.floor(Math.random() * this.q.length)];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = new RandomizedSet()
 * var param_1 = obj.insert(val)
 * var param_2 = obj.remove(val)
 * var param_3 = obj.getRandom()
 */
