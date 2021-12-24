function findCenter(edges: number[][]): number {
    for (let num of edges[0]) {
        if (edges[1].includes(num)) {
            return num;
        }
    }
}
