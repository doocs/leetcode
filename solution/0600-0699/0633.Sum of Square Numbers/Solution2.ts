function judgeSquareSum(c: number): boolean {
    for (let a = 0, inc = -1; a <= c; inc += 2, a += inc) {
        const b = Math.sqrt(c - a);
        if (b === (b | 0)) return true;
    }

    return false;
}
