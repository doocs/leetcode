function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const vis: boolean[] = Array(n).fill(false);
    const dfs = (i: number) => {
        if (vis[i]) {
            return;
        }
        vis[i] = true;
        for (const j of rooms[i]) {
            dfs(j);
        }
    };
    dfs(0);
    return vis.every(v => v);
}
