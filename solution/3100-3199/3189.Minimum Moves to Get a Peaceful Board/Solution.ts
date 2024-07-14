function minMoves(rooks: number[][]): number {
    rooks.sort((a, b) => a[0] - b[0]);
    let ans = rooks.reduce((sum, rook, i) => sum + Math.abs(rook[0] - i), 0);
    rooks.sort((a, b) => a[1] - b[1]);
    ans += rooks.reduce((sum, rook, j) => sum + Math.abs(rook[1] - j), 0);
    return ans;
}
