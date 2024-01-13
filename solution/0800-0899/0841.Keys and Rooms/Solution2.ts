function canVisitAllRooms(rooms: number[][]): boolean {
    const n = rooms.length;
    const isOpen = new Array(n).fill(false);
    const keys = [0];
    while (keys.length !== 0) {
        const i = keys.pop();
        if (isOpen[i]) {
            continue;
        }
        isOpen[i] = true;
        keys.push(...rooms[i]);
    }
    return isOpen.every(v => v);
}
