function findClosedNumbers(num: number): number[] {
    const ans: number[] = [-1, -1];
    const dirs: number[] = [0, 1, 0];
    for (let p = 0; p < 2; ++p) {
        const [a, b] = [dirs[p], dirs[p + 1]];
        let x = num;
        for (let i = 1; i < 31; ++i) {
            if (((x >> i) & 1) === a && ((x >> (i - 1)) & 1) === b) {
                x ^= 1 << i;
                x ^= 1 << (i - 1);
                let [j, k] = [0, i - 2];
                while (j < k) {
                    while (j < k && ((x >> j) & 1) === b) {
                        ++j;
                    }
                    while (j < k && ((x >> k) & 1) === a) {
                        --k;
                    }
                    if (j < k) {
                        x ^= 1 << j;
                        x ^= 1 << k;
                    }
                }
                ans[p] = x;
                break;
            }
        }
    }
    return ans;
}
