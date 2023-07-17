function rampartDefensiveLine(rampart: number[][]): number {
    const check = (w: number): boolean => {
        let last = rampart[0][1];
        for (let i = 1; i < rampart.length - 1; ++i) {
            const [x, y] = rampart[i];
            const a = x - last;
            const b = Math.max(w - a, 0);
            if (y + b > rampart[i + 1][0]) {
                return false;
            }
            last = y + b;
        }
        return true;
    };
    let left = 0;
    let right = rampart[1][0] - rampart[0][1] + rampart[2][0] - rampart[1][1];
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
}
