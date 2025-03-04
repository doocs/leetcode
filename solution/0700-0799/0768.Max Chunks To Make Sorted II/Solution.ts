function maxChunksToSorted(arr: number[]): number {
    const stk: number[] = [];
    for (let v of arr) {
        if (stk.length === 0 || v >= stk[stk.length - 1]) {
            stk.push(v);
        } else {
            let mx = stk.pop()!;
            while (stk.length > 0 && stk[stk.length - 1] > v) {
                stk.pop();
            }
            stk.push(mx);
        }
    }
    return stk.length;
}
