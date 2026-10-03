function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const vis: boolean[] = Array(n).fill(false);
    const stk: number[] = [0];
    while (stk.length) {
        const i = stk.pop()!;
        if (vis[i]) {
            continue;
        }
        vis[i] = true;
        for (const j of rooms[i]) {
            stk.push(j);
        }
    }
    return vis.every(v => v);
}
