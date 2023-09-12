function maxTotalFruits(fruits: number[][], startPos: number, k: number): number {
    let ans = 0;
    let s = 0;
    for (let i = 0, j = 0; j < fruits.length; ++j) {
        const [pj, fj] = fruits[j];
        s += fj;
        while (
            i <= j &&
            pj -
                fruits[i][0] +
                Math.min(Math.abs(startPos - fruits[i][0]), Math.abs(startPos - pj)) >
                k
        ) {
            s -= fruits[i++][1];
        }
        ans = Math.max(ans, s);
    }
    return ans;
}
