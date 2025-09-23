export function rearrangeString(s: string, k: number): string {
    const cnt: number[] = Array(26).fill(0);
    for (const c of s) {
        cnt[c.charCodeAt(0) - 'a'.charCodeAt(0)]++;
    }

    const pq = new PriorityQueue<[number, number]>((a, b) => b[0] - a[0]);
    for (let i = 0; i < 26; i++) {
        if (cnt[i] > 0) {
            pq.enqueue([cnt[i], i]);
        }
    }

    const q: [number, number][] = [];
    const ans: string[] = [];
    while (!pq.isEmpty()) {
        const [count, idx] = pq.dequeue()!;
        const newCount = count - 1;
        ans.push(String.fromCharCode('a'.charCodeAt(0) + idx));
        q.push([newCount, idx]);
        if (q.length >= k) {
            const [frontCount, frontIdx] = q.shift()!;
            if (frontCount > 0) {
                pq.enqueue([frontCount, frontIdx]);
            }
        }
    }
    return ans.length < s.length ? '' : ans.join('');
}
