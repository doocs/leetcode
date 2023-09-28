function minPushBox(grid: string[][]): number {
    const [m, n] = [grid.length, grid[0].length];
    let [si, sj, bi, bj] = [0, 0, 0, 0];
    for (let i = 0; i < m; ++i) {
        for (let j = 0; j < n; ++j) {
            if (grid[i][j] === 'S') {
                [si, sj] = [i, j];
            } else if (grid[i][j] === 'B') {
                [bi, bj] = [i, j];
            }
        }
    }
    const f = (i: number, j: number): number => i * n + j;
    const check = (i: number, j: number): boolean =>
        i >= 0 && i < m && j >= 0 && j < n && grid[i][j] !== '#';

    const q: Deque<[number, number, number]> = new Deque();
    const vis: boolean[][] = new Array(m * n).fill(0).map(() => new Array(m * n).fill(false));
    q.push([f(si, sj), f(bi, bj), 0]);
    vis[f(si, sj)][f(bi, bj)] = true;
    const dirs: number[] = [-1, 0, 1, 0, -1];
    while (q.size() > 0) {
        const [s, b, d] = q.shift()!;
        const [si, sj] = [Math.floor(s / n), s % n];
        const [bi, bj] = [Math.floor(b / n), b % n];
        if (grid[bi][bj] === 'T') {
            return d;
        }
        for (let k = 0; k < 4; ++k) {
            const [sx, sy] = [si + dirs[k], sj + dirs[k + 1]];
            if (!check(sx, sy)) {
                continue;
            }
            if (sx === bi && sy === bj) {
                const [bx, by] = [bi + dirs[k], bj + dirs[k + 1]];
                if (!check(bx, by) || vis[f(sx, sy)][f(bx, by)]) {
                    continue;
                }
                vis[f(sx, sy)][f(bx, by)] = true;
                q.push([f(sx, sy), f(bx, by), d + 1]);
            } else if (!vis[f(sx, sy)][f(bi, bj)]) {
                vis[f(sx, sy)][f(bi, bj)] = true;
                q.unshift([f(sx, sy), f(bi, bj), d]);
            }
        }
    }
    return -1;
}

/* 以下是双向列队模板类 */
class CircularDeque<T> {
    prev: CircularDeque<T> | null;
    next: CircularDeque<T> | null;
    begin: number;
    end: number;
    empty: boolean;
    data: T[];
    constructor(N: number) {
        this.prev = this.next = null;
        this.begin = this.end = 0;
        this.empty = true;
        this.data = Array(N);
    }

    isFull(): boolean {
        return this.end === this.begin && !this.empty;
    }

    isEmpty(): boolean {
        return this.empty;
    }

    push(val: T): boolean {
        if (this.isFull()) return false;
        this.empty = false;
        this.data[this.end] = val;
        this.end = (this.end + 1) % this.data.length;
        return true;
    }

    front(): T | undefined {
        return this.isEmpty() ? undefined : this.data[this.begin];
    }

    back(): T | undefined {
        return this.isEmpty() ? undefined : this.data[this.end - 1];
    }

    pop(): T | undefined {
        if (this.isEmpty()) return undefined;
        const value = this.data[this.end - 1];
        this.end = (this.end - 1) % this.data.length;
        if (this.end < 0) this.end += this.data.length;
        if (this.end === this.begin) this.empty = true;
        return value;
    }

    unshift(val: T): boolean {
        if (this.isFull()) return false;
        this.empty = false;
        this.begin = (this.begin - 1) % this.data.length;
        if (this.begin < 0) this.begin += this.data.length;
        this.data[this.begin] = val;
        return true;
    }

    shift(): T | undefined {
        if (this.isEmpty()) return undefined;
        const value = this.data[this.begin];
        this.begin = (this.begin + 1) % this.data.length;
        if (this.end === this.begin) this.empty = true;
        return value;
    }

    *values(): Generator<T, void, undefined> {
        if (this.isEmpty()) return undefined;
        let i = this.begin;
        do {
            yield this.data[i];
            i = (i + 1) % this.data.length;
        } while (i !== this.end);
    }
}

class Deque<T> {
    head: CircularDeque<T>;
    tail: CircularDeque<T>;
    _size: number;
    constructor(collection: T[] = []) {
        this.head = new CircularDeque<T>(128);
        this.tail = new CircularDeque<T>(128);
        this.tail.empty = this.head.empty = false;
        this.tail.prev = this.head;
        this.head.next = this.tail;
        this._size = 0;
        for (const item of collection) this.push(item);
    }

    size(): number {
        return this._size;
    }

    push(val: T): void {
        let last = this.tail.prev!;
        if (last.isFull()) {
            const inserted = new CircularDeque<T>(128);

            this.tail.prev = inserted;
            inserted.next = this.tail;

            last.next = inserted;
            inserted.prev = last;

            last = inserted;
        }
        last.push(val);
        this._size++;
    }

    back(): T | undefined {
        if (this._size === 0) return;
        return this.tail.prev!.back();
    }

    pop(): T | undefined {
        if (this.head.next === this.tail) return undefined;
        const last = this.tail.prev!;
        const value = last.pop();
        if (last.isEmpty()) {
            this.tail.prev = last.prev;
            last.prev!.next = this.tail;
        }
        this._size--;
        return value;
    }

    unshift(val: T): void {
        let first = this.head.next!;
        if (first.isFull()) {
            const inserted = new CircularDeque<T>(128);

            this.head.next = inserted;
            inserted.prev = this.head;

            inserted.next = first;
            first.prev = inserted;

            first = inserted;
        }
        first.unshift(val);
        this._size++;
    }

    shift(): T | undefined {
        if (this.head.next === this.tail) return undefined;
        const first = this.head.next!;
        const value = first.shift();
        if (first.isEmpty()) {
            this.head.next = first.next;
            first.next!.prev = this.head;
        }
        this._size--;
        return value;
    }

    front(): T | undefined {
        if (this._size === 0) return undefined;
        return this.head.next!.front();
    }

    *values(): Generator<T, void, undefined> {
        let node = this.head.next!;
        while (node !== this.tail) {
            for (const value of node.values()) yield value;
            node = node.next!;
        }
    }
}
