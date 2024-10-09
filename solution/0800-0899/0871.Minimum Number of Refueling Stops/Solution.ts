function minRefuelStops(target: number, startFuel: number, stations: number[][]): number {
    const pq = new MaxPriorityQueue();
    let [ans, pre] = [0, 0];
    stations.push([target, 0]);
    for (const [pos, fuel] of stations) {
        const dist = pos - pre;
        startFuel -= dist;
        while (startFuel < 0 && !pq.isEmpty()) {
            startFuel += pq.dequeue().element;
            ans++;
        }
        if (startFuel < 0) {
            return -1;
        }
        pq.enqueue(fuel);
        pre = pos;
    }
    return ans;
}
