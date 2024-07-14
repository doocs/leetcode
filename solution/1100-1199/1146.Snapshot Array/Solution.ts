class SnapshotArray {
    private arr: [number, number][][];
    private i: number = 0;
    constructor(length: number) {
        this.arr = Array.from({ length }, () => []);
    }

    set(index: number, val: number): void {
        this.arr[index].push([this.i, val]);
    }

    snap(): number {
        return this.i++;
    }

    get(index: number, snap_id: number): number {
        let [l, r] = [0, this.arr[index].length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (this.arr[index][mid][0] > snap_id) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        --l;
        return l < 0 ? 0 : this.arr[index][l][1];
    }
}

/**
 * Your SnapshotArray object will be instantiated and called as such:
 * var obj = new SnapshotArray(length)
 * obj.set(index,val)
 * var param_2 = obj.snap()
 * var param_3 = obj.get(index,snap_id)
 */
