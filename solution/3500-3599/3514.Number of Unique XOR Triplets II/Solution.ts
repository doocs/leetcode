function uniqueXorTriplets(nums: number[]): number {
    const mx = Math.max(...nums) << 1;

    const st = new Array<boolean>(mx).fill(false);
    for (const a of nums) {
        for (const b of nums) {
            st[a ^ b] = true;
        }
    }

    const s = new Array<number>(mx).fill(0);
    for (let ab = 0; ab < mx; ab++) {
        if (st[ab]) {
            for (const c of nums) {
                s[ab ^ c] = 1;
            }
        }
    }

    let ans = 0;
    for (const v of s) {
        ans += v;
    }
    return ans;
}
