function maximumSaleItems(items: number[][], budget: number): number {
    const n = items.length;
    const have: number[] = Array(n + 1).fill(0); // have[f]: items with factor f
    let cheapest = Infinity;
    for (const [factor, price] of items) {
        have[factor]++;
        cheapest = Math.min(cheapest, price);
    }
    const deals: number[] = Array(n + 1).fill(-1); // counted when an item is reached

    const worth = items.filter(([, price]) => price < 2 * cheapest);
    const heap = MinPriorityQueue.fromArray(worth, ([, price]) => price); // built in O(n)
    let copies = 0;
    while (!heap.isEmpty() && heap.front()![1] <= budget) {
        // the cheapest deal left is affordable
        const [factor, price] = heap.dequeue()!;
        if (deals[factor] < 0)
            // from -1: the item itself is not a deal
            for (let multiple = factor; multiple <= n; multiple += factor)
                deals[factor] += have[multiple];
        const pairs = Math.min(deals[factor], Math.floor(budget / price));
        copies += 2 * pairs;
        budget -= pairs * price;
    }
    return copies + Math.floor(budget / cheapest);
}
