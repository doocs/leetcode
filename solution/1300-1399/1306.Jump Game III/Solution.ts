function canReach(arr: number[], start: number): boolean {
    const q: number[] = [start];
    while (q.length) {
        const i: number = q.shift()!;
        if (arr[i] === 0) {
            return true;
        }
        const x: number = arr[i];
        arr[i] = -1;
        for (const j of [i + x, i - x]) {
            if (j >= 0 && j < arr.length && arr[j] !== -1) {
                q.push(j);
            }
        }
    }
    return false;
}
