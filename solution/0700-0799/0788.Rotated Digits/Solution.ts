function rotatedDigits(n: number): number {
    const d: number[] = [0, 1, 5, -1, -1, 2, 9, -1, 8, 6];
    const check = (x: number): boolean => {
        let y = 0;
        let t = x;
        let k = 1;

        while (t > 0) {
            const v = t % 10;
            if (d[v] === -1) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t = Math.floor(t / 10);
        }
        return x !== y;
    };
    return Array.from({ length: n }, (_, i) => i + 1).filter(check).length;
}
