const p: Map<number, number> = new Map();
    let r: number = Number.MIN_SAFE_INTEGER;
    p.set(nums[0], 0);
    let s: number = 0;
    const n: number = nums.length;
    for (let i = 0; ; ++i) {
        s += nums[i];
        let t: number | undefined = p.get(nums[i] - k);
        if (t !== undefined) {
            r = Math.max(r, s - t);
        }
        t = p.get(nums[i] + k);
        if (t !== undefined) {
            r = Math.max(r, s - t);
        }
        if (i + 1 === n)
            break;
        t = p.get(nums[i + 1]);
        if (t === undefined || t > s) {
            p.set(nums[i + 1], s);
        }
    }
    return r === Number.MIN_SAFE_INTEGER ? 0 : r;
