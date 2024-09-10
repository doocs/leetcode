function minAvailableDuration(slots1: number[][], slots2: number[][], duration: number): number[] {
    slots1.sort((a, b) => a[0] - b[0]);
    slots2.sort((a, b) => a[0] - b[0]);
    const [m, n] = [slots1.length, slots2.length];
    let [i, j] = [0, 0];
    while (i < m && j < n) {
        const [start1, end1] = slots1[i];
        const [start2, end2] = slots2[j];
        const start = Math.max(start1, start2);
        const end = Math.min(end1, end2);
        if (end - start >= duration) {
            return [start, start + duration];
        }
        if (end1 < end2) {
            i++;
        } else {
            j++;
        }
    }
    return [];
}
