function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const isOpen = new Array(n).fill(false);
    const dfs = (i: number) => {
        if (isOpen[i]) {
            return;
        }
        isOpen[i] = true;
        rooms[i].forEach(k => dfs(k));
    };
    dfs(0);
    return isOpen.every(v => v);
}
