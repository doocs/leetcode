function longestSubarray(nums: number[], limit: number): number {
    let [ans, l, r, n] = [0, 0, 0, nums.length];

    const minHeap = new MinHeap();
    const maxHeap = new MaxHeap();

    minHeap.push(nums[r]);
    maxHeap.push(nums[r]);

    while (r < n) {
        const diff = maxHeap.peek()! - minHeap.peek()!;

        if (diff <= limit) {
            ans = Math.max(ans, minHeap.size);
            r++;
            if (r < n) {
                minHeap.push(nums[r]);
                maxHeap.push(nums[r]);
            }
        } else {
            minHeap.remove(nums[l]);
            maxHeap.remove(nums[l]);
            l++;
        }
    }

    return ans;
}

class MinHeap<T = number> {
    #h: T[] = [];

    get size() {
        return this.#h.length;
    }

    push(x: T) {
        const h = this.#h;

        h.push(x);
        if (h.length === 1) return;

        let i = h.length - 1;
        while (i !== undefined) {
            const p = i >> 1;
            if (h[p] <= h[i]) return;
            this.#swap(i, p);
            i = p;
        }
    }

    pop(): T | undefined {
        return this.#sinkingDown();
    }

    remove(v: T) {
        const i = this.#h.indexOf(v);
        if (i === -1) return;
        this.#sinkingDown(i);
    }

    peek(): T | undefined {
        return this.#h[0];
    }

    #sinkingDown(i = 0): T | undefined {
        const h = this.#h;

        if (h.length <= 2) {
            const val = this.#h.splice(i, 1)[0];
            return val;
        }

        this.#swap(i, h.length - 1);
        const val = h.pop();

        while (true) {
            const l = 2 * i + 1;
            const r = 2 * i + 2;
            const child = h[l] && h[r] ? (h[l] <= h[r] ? l : r) : h[l] ? l : r;

            if (!h[child] || h[i] <= h[child]) break;

            this.#swap(i, child);
            i = child;
        }

        return val;
    }

    #swap(i: number, j: number) {
        const h = this.#h;
        [h[i], h[j]] = [h[j], h[i]];
    }

    *[Symbol.iterator]() {
        const h = [...this.#h];
        while (this.#h.length) yield this.pop();
        this.#h = h;
    }
}

export class MaxHeap extends MinHeap {
    push(x: number): void {
        super.push(-x);
    }

    pop(): number | undefined {
        const res = super.pop();
        if (res === undefined) return;
        return -res;
    }

    peek(): number | undefined {
        const res = super.peek();
        if (res === undefined) return;
        return -res;
    }

    remove(v: number): void {
        super.remove(-v);
    }
}
