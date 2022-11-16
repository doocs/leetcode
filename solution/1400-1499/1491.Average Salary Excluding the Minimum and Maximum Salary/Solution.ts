function average(salary: number[]): number {
    let max = -Infinity;
    let min = Infinity;
    let sum = 0;
    for (const v of salary) {
        sum += v;
        max = Math.max(max, v);
        min = Math.min(min, v);
    }
    return (sum - max - min) / (salary.length - 2);
}
