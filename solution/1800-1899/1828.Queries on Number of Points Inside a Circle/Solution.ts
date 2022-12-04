function countPoints(points: number[][], queries: number[][]): number[] {
    return queries.map(([cx, cy, r]) => {
        let res = 0;
        for (const [px, py] of points) {
            if (Math.sqrt((cx - px) ** 2 + (cy - py) ** 2) <= r) {
                res++;
            }
        }
        return res;
    });
}
