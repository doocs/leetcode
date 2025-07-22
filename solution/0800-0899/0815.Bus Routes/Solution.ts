function numBusesToDestination(routes: number[][], source: number, target: number): number {
    if (source === target) {
        return 0;
    }

    const g: Map<number, number[]> = new Map();
    for (let i = 0; i < routes.length; i++) {
        for (const stop of routes[i]) {
            if (!g.has(stop)) {
                g.set(stop, []);
            }
            g.get(stop)!.push(i);
        }
    }

    if (!g.has(source) || !g.has(target)) {
        return -1;
    }

    const q: [number, number][] = [[source, 0]];
    const visBus: Set<number> = new Set();
    const visStop: Set<number> = new Set([source]);

    for (const [stop, busCount] of q) {
        if (stop === target) {
            return busCount;
        }
        for (const bus of g.get(stop)!) {
            if (!visBus.has(bus)) {
                visBus.add(bus);
                for (const nextStop of routes[bus]) {
                    if (!visStop.has(nextStop)) {
                        visStop.add(nextStop);
                        q.push([nextStop, busCount + 1]);
                    }
                }
            }
        }
    }
    return -1;
}
