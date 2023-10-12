function minDominoRotations(tops: number[], bottoms: number[]): number {
    const n = tops.length;
    const f = (x: number): number => {
        let [cnt1, cnt2] = [0, 0];
        for (let i = 0; i < n; ++i) {
            if (tops[i] !== x && bottoms[i] !== x) {
                return n + 1;
            }
            cnt1 += tops[i] === x ? 1 : 0;
            cnt2 += bottoms[i] === x ? 1 : 0;
        }
        return n - Math.max(cnt1, cnt2);
    };
    const ans = Math.min(f(tops[0]), f(bottoms[0]));
    return ans > n ? -1 : ans;
}
