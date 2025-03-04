function maxDistance(s: string, k: number): number {
    const calc = (a: string, b: string): number => {
        let [ans, mx, cnt] = [0, 0, 0];
        for (const c of s) {
            if (c === a || c === b) {
                ++mx;
            } else if (cnt < k) {
                ++mx;
                ++cnt;
            } else {
                --mx;
            }
            ans = Math.max(ans, mx);
        }
        return ans;
    };
    const a = calc('S', 'E');
    const b = calc('S', 'W');
    const c = calc('N', 'E');
    const d = calc('N', 'W');
    return Math.max(a, b, c, d);
}
