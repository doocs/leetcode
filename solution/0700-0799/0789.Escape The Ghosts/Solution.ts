function escapeGhosts(ghosts: number[][], target: number[]): boolean {
    const [tx, ty] = target;
    for (const [x, y] of ghosts) {
        if (
            Math.abs(tx - x) + Math.abs(ty - y) <=
            Math.abs(tx) + Math.abs(ty)
        ) {
            return false;
        }
    }
    return true;
}
