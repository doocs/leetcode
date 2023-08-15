function perfectMenu(
    materials: number[],
    cookbooks: number[][],
    attribute: number[][],
    limit: number,
): number {
    const n = cookbooks.length;
    let ans = -1;
    for (let mask = 0; mask < 1 << n; ++mask) {
        let [a, b] = [0, 0];
        const cnt: number[] = Array(5).fill(0);
        for (let i = 0; i < n; ++i) {
            if (((mask >> i) & 1) === 1) {
                const [x, y] = attribute[i];
                a += x;
                b += y;
                for (let j = 0; j < cookbooks[i].length; ++j) {
                    cnt[j] += cookbooks[i][j];
                }
            }
            let ok = true;
            for (let i = 0; i < 5 && ok; ++i) {
                ok = cnt[i] <= materials[i];
            }
            if (b >= limit && ans < a && ok) {
                ans = a;
            }
        }
    }
    return ans;
}
