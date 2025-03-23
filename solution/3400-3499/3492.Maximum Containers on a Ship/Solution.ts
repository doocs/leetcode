function maxContainers(n: number, w: number, maxWeight: number): number {
    return (Math.min(n * n * w, maxWeight) / w) | 0;
}
