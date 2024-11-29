class MinHeap {
    constructor() {
        this.heap = [];
    }

    push(element) {
        this.heap.push(element);
        this._heapifyUp();
    }

    pop() {
        if (this.size() === 1) return this.heap.pop();
        const top = this.heap[0];
        this.heap[0] = this.heap.pop();
        this._heapifyDown();
        return top;
    }

    peek() {
        return this.heap[0];
    }

    size() {
        return this.heap.length;
    }

    _heapifyUp() {
        let index = this.heap.length - 1;
        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            if (this.heap[index][0] >= this.heap[parentIndex][0]) break;
            [this.heap[index], this.heap[parentIndex]] = [this.heap[parentIndex], this.heap[index]];
            index = parentIndex;
        }
    }

    _heapifyDown() {
        let index = 0;
        const length = this.heap.length;
        while (true) {
            const leftChild = 2 * index + 1;
            const rightChild = 2 * index + 2;
            let smallest = index;

            if (leftChild < length && this.heap[leftChild][0] < this.heap[smallest][0]) {
                smallest = leftChild;
            }
            if (rightChild < length && this.heap[rightChild][0] < this.heap[smallest][0]) {
                smallest = rightChild;
            }
            if (smallest === index) break;

            [this.heap[index], this.heap[smallest]] = [this.heap[smallest], this.heap[index]];
            index = smallest;
        }
    }
}

/**
 * @param {number[][]} grid
 * @return {number}
 */
var minimumTime = function(grid) {
    const m = grid.length;
        const n = grid[0].length;

        if (grid[0][1] > 1 && grid[1][0] > 1) return -1;

        const dist = Array.from({ length: m }, () => Array(n).fill(Infinity));
        dist[0][0] = 0;

        const pq = new MinHeap();
        pq.push([0, 0, 0]);

        const directions = [-1, 0, 1, 0, -1];

        while (pq.size() > 0) {
            const [t, i, j] = pq.pop();

            if (i === m - 1 && j === n - 1) return t;

            for (let d = 0; d < 4; d++) {
                const x = i + directions[d];
                const y = j + directions[d + 1];

                if (x >= 0 && x < m && y >= 0 && y < n) {
                    let nt = t + 1;
                    if (nt < grid[x][y]) {
                        nt = grid[x][y] + (grid[x][y] - nt) % 2;
                    }

                    if (nt < dist[x][y]) {
                        dist[x][y] = nt;
                        pq.push([nt, x, y]);
                    }
                }
            }
        }

        return -1;
};
