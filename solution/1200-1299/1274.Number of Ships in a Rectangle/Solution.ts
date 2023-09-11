/**
 * // This is the Sea's API interface.
 * // You should not implement it, or speculate about its implementation
 * class Sea {
 *      hasShips(topRight: number[], bottomLeft: number[]): boolean {}
 * }
 */

function countShips(sea: Sea, topRight: number[], bottomLeft: number[]): number {
    const [x1, y1] = bottomLeft;
    const [x2, y2] = topRight;
    if (x1 > x2 || y1 > y2 || !sea.hasShips(topRight, bottomLeft)) {
        return 0;
    }
    if (x1 === x2 && y1 === y2) {
        return 1;
    }
    const midx = (x1 + x2) >> 1;
    const midy = (y1 + y2) >> 1;
    const a = countShips(sea, topRight, [midx + 1, midy + 1]);
    const b = countShips(sea, [midx, y2], [x1, midy + 1]);
    const c = countShips(sea, [midx, midy], bottomLeft);
    const d = countShips(sea, [x2, midy], [midx + 1, y1]);
    return a + b + c + d;
}
