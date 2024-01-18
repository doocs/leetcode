function countQuadruples(firstString: string, secondString: string): number {
    const last: number[] = new Array(26).fill(0);
    for (let i = 0; i < secondString.length; ++i) {
        last[secondString.charCodeAt(i) - 97] = i + 1;
    }
    let [ans, mi] = [0, Infinity];
    for (let i = 0; i < firstString.length; ++i) {
        const j = last[firstString.charCodeAt(i) - 97];
        if (j) {
            const t = i - j;
            if (mi > t) {
                mi = t;
                ans = 1;
            } else if (mi === t) {
                ++ans;
            }
        }
    }
    return ans;
}
