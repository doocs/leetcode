function makeParityAlternating(nums: number[]): number[] {
    if (nums.length === 1) {
        return [0, 0];
    }

    const mn = Math.min(...nums);
    const mx = Math.max(...nums);

    const f = (k: number): number[] => {
        let cnt = 0;
        let a = Number.MAX_SAFE_INTEGER;
        let b = Number.MIN_SAFE_INTEGER;

        for (let i = 0; i < nums.length; i++) {
            let x = nums[i];
            if (((x - i) & 1) !== k) {
                cnt++;
                if (x === mn) {
                    ++x;
                } else if (x === mx) {
                    --x;
                }
            }
            a = Math.min(a, x);
            b = Math.max(b, x);
        }
        return [cnt, Math.max(1, b - a)];
    };

    const r0 = f(0);
    const r1 = f(1);

    if (r0[0] !== r1[0]) {
        return r0[0] < r1[0] ? r0 : r1;
    }
    return r0[1] <= r1[1] ? r0 : r1;
}
