function canVisitAllRooms(rooms: number[][]): boolean {
    const vis = new Set<number>();
    const q: number[] = [0];

    while (q.length) {
        const i = q.pop()!;
        if (vis.has(i)) {
            continue;
        }
        vis.add(i);
        q.push(...rooms[i]);
    }

    return vis.size == rooms.length;
}
