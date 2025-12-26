const smallestRange = (nums: number[][]): number[] => {
    const pq = new PriorityQueue<number[]>((a, b) => a[0] - b[0]);
    const n = nums.length;
    let [l, r, max] = [0, Number.POSITIVE_INFINITY, Number.NEGATIVE_INFINITY];

    for (let j = 0; j < n; j++) {
        const x = nums[j][0];
        pq.enqueue([x, j, 0]);
        max = Math.max(max, x);
    }

    while (pq.size() === n) {
        const [min, j, i] = pq.dequeue();

        if (max - min < r - l) {
            [l, r] = [min, max];
        }

        const iNext = i + 1;
        if (iNext < nums[j].length) {
            const next = nums[j][iNext];
            pq.enqueue([next, j, iNext]);
            max = Math.max(max, next);
        }
    }

    return [l, r];
};
