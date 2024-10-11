/**
 * @param {number[][]} times
 * @param {number} targetFriend
 * @return {number}
 */
var smallestChair = function(times, targetFriend) {
    const n = times.length;

    const availableChairs = new MinHeap();

    const busy = new MinHeap((a, b) => a[0] - b[0]);  

    for (let i = 0; i < n; ++i) {
        times[i].push(i);  
        availableChairs.push(i); 
    }

    times.sort((a, b) => a[0] - b[0]);

    for (let t of times) {
        const arrival = t[0], leaving = t[1], friendIndex = t[2];

        while (!busy.isEmpty() && busy.peek()[0] <= arrival) {
            availableChairs.push(busy.pop()[1]);
        }

        const chair = availableChairs.pop();

        if (friendIndex === targetFriend) {
            return chair;
        }

        busy.push([leaving, chair]);
    }

    return -1;  
};

class MinHeap {
    constructor(compare = (a, b) => a - b) {
        this.heap = [];
        this.compare = compare;
    }

    push(val) {
        this.heap.push(val);
        this.bubbleUp(this.heap.length - 1);
    }

    pop() {
        const top = this.heap[0];
        const bottom = this.heap.pop();
        if (this.heap.length > 0) {
            this.heap[0] = bottom;
            this.bubbleDown(0);
        }
        return top;
    }

    peek() {
        return this.heap[0];
    }

    isEmpty() {
        return this.heap.length === 0;
    }

    bubbleUp(index) {
        while (index > 0) {
            const parentIndex = Math.floor((index - 1) / 2);
            if (this.compare(this.heap[index], this.heap[parentIndex]) < 0) {
                [this.heap[index], this.heap[parentIndex]] = [this.heap[parentIndex], this.heap[index]];
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    bubbleDown(index) {
        const length = this.heap.length;
        while (true) {
            const leftIndex = 2 * index + 1;
            const rightIndex = 2 * index + 2;
            let smallest = index;

            if (leftIndex < length && this.compare(this.heap[leftIndex], this.heap[smallest]) < 0) {
                smallest = leftIndex;
            }
            if (rightIndex < length && this.compare(this.heap[rightIndex], this.heap[smallest]) < 0) {
                smallest = rightIndex;
            }
            if (smallest !== index) {
                [this.heap[index], this.heap[smallest]] = [this.heap[smallest], this.heap[index]];
                index = smallest;
            } else {
                break;
            }
        }
    }
}
