function minimumOperations(nums: number[]): number {
    const f = (i: number): [number, number, number, number] => {
        const cnt: Map<number, number> = new Map();
        for (; i < nums.length; i += 2) {
            cnt.set(nums[i], (cnt.get(nums[i]) || 0) + 1);
        }

        let [k1, k2] = [0, 0];
        for (const [k, v] of cnt) {
            if ((cnt.get(k1) || 0) < v) {
                k2 = k1;
                k1 = k;
            } else if ((cnt.get(k2) || 0) < v) {
                k2 = k;
            }
        }
        return [k1, cnt.get(k1) || 0, k2, cnt.get(k2) || 0];
    };

    const a = f(0);
    const b = f(1);
    const n = nums.length;
    if (a[0] !== b[0]) {
        return n - (a[1] + b[1]);
    }
    return n - Math.max(a[1] + b[3], a[3] + b[1]);
}
