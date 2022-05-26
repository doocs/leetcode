function missingRolls(rolls: number[], mean: number, n: number): number[] {
    const len = rolls.length + n;
    const sum = rolls.reduce((p, v) => p + v);
    const max = n * 6;
    const min = n;
    if ((sum + max) / len < mean || (sum + min) / len > mean) {
        return [];
    }

    const res = new Array(n);
    for (let i = min; i <= max; i++) {
        if ((sum + i) / len === mean) {
            const num = Math.floor(i / n);
            res.fill(num);
            let count = i - n * num;
            let j = 0;
            while (count != 0) {
                if (res[j] === 6) {
                    j++;
                } else {
                    res[j]++;
                    count--;
                }
            }
            break;
        }
    }
    return res;
}
