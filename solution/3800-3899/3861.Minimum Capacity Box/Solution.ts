function minimumIndex(capacity: number[], itemSize: number): number {
    let ans = -1;
    for (let i = 0; i < capacity.length; ++i) {
        const x = capacity[i];
        if (x >= itemSize && (ans === -1 || x < capacity[ans])) {
            ans = i;
        }
    }
    return ans;
}
