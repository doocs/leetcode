class RandomizedSet {
    public map: Map<number, number>;
    public arr: number[];
    public index: number;

    constructor() {
        this.map = new Map();
        this.arr = new Array(2 * 10 ** 5).fill(0);
        this.index = -1;
    }

    insert(val: number): boolean {
        const { map, arr } = this;
        if (map.has(val)) {
            return false;
        }
        this.index++;
        arr[this.index] = val;
        map.set(val, this.index);
        return true;
    }

    remove(val: number): boolean {
        const { arr, map, index } = this;
        if (!map.has(val)) {
            return false;
        }
        const i = map.get(val);
        [arr[i], arr[index]] = [arr[index], arr[i]];
        map.set(arr[i], i);
        map.delete(arr[index]);
        this.index--;
        return true;
    }

    getRandom(): number {
        const i = Math.floor(Math.random() * (this.index + 1));
        return this.arr[i];
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * var obj = new RandomizedSet()
 * var param_1 = obj.insert(val)
 * var param_2 = obj.remove(val)
 * var param_3 = obj.getRandom()
 */
