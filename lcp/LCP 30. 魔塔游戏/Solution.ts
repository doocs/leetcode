function magicTower(nums: number[]): number {
    const q = new MinPriorityQueue();
    let [ans, blood, v] = [0, 1, 0];
    for (const x of nums) {
        if (x < 0) {
            q.enqueue(x);
        }
        blood += x;
        if (blood <= 0) {
            ++ans;
            const t = q.dequeue().element;
            blood -= t;
            v += t;
        }
    }
    return blood + v <= 0 ? -1 : ans;
}
