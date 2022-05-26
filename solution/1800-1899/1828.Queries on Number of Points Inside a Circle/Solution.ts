function countPoints(points: number[][], queries: number[][]): number[] {
    let ans = [];
    for (let [cx, cy, r] of queries) {
        let square = r ** 2;
        let count = 0;
        for (let [px, py] of points) {
            if ((px - cx) ** 2 + (py - cy) ** 2 <= square) {
                ++count;
            }
        }
        ans.push(count);
    }
    return ans;
}
