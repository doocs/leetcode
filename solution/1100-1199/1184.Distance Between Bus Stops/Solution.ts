function distanceBetweenBusStops(distance: number[], start: number, destination: number): number {
    const s = distance.reduce((a, b) => a + b, 0);
    const n = distance.length;
    let t = 0;
    while (start !== destination) {
        t += distance[start];
        start = (start + 1) % n;
    }
    return Math.min(t, s - t);
}
