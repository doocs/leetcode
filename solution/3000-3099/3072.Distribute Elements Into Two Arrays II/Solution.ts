class BinaryIndexedTree {
    private n: number;
    private c: number[];

    constructor(n: number) {
        this.n = n;
        this.c = Array(n + 1).fill(0);
    }

    update(x: number, delta: number): void {
        for (; x <= this.n; x += x & -x) {
            this.c[x] += delta;
        }
    }

    query(x: number): number {
        let s = 0;
        for (; x > 0; x -= x & -x) {
            s += this.c[x];
        }
        return s;
    }
}

function resultArray(nums: number[]): number[] {
    const st: number[] = nums.slice().sort((a, b) => a - b);
    const n: number = st.length;
    const search = (x: number): number => {
        let [l, r] = [0, n];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (st[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const tree1: BinaryIndexedTree = new BinaryIndexedTree(n + 1);
    const tree2: BinaryIndexedTree = new BinaryIndexedTree(n + 1);
    tree1.update(search(nums[0]) + 1, 1);
    tree2.update(search(nums[1]) + 1, 1);
    const arr1: number[] = [nums[0]];
    const arr2: number[] = [nums[1]];
    for (const x of nums.slice(2)) {
        const i: number = search(x) + 1;
        const a: number = arr1.length - tree1.query(i);
        const b: number = arr2.length - tree2.query(i);
        if (a > b) {
            arr1.push(x);
            tree1.update(i, 1);
        } else if (a < b) {
            arr2.push(x);
            tree2.update(i, 1);
        } else if (arr1.length <= arr2.length) {
            arr1.push(x);
            tree1.update(i, 1);
        } else {
            arr2.push(x);
            tree2.update(i, 1);
        }
    }
    return arr1.concat(arr2);
}
