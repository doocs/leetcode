function findMaxSum(nums1: number[], nums2: number[], k: number): number[] {
    const n = nums1.length;
    const arr = nums1.map((x, i) => [x, i]).sort((a, b) => a[0] - b[0]);
    const pq = new MinPriorityQueue<number>();
    let [s, j] = [0, 0];
    const ans: number[] = Array(k).fill(0);
    for (let h = 0; h < n; ++h) {
        const [x, i] = arr[h];
        while (j < h && arr[j][0] < x) {
            const y = nums2[arr[j++][1]];
            pq.enqueue(y);
            s += y;
            if (pq.size() > k) {
                s -= pq.dequeue();
            }
        }
        ans[i] = s;
    }
    return ans;
}
