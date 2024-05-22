function canVisitAllRooms(rooms: number[][]): boolean {
    const seen = new Set<number>();
    const queue = [0];

    while (queue.length) {
        const room = queue.pop()!;

        if (seen.has(room)) continue;
        seen.add(room);
        queue.push(...rooms[room]);
    }

    const res = rooms.length === seen.size;

    return res;
}
