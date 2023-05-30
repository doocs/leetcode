function maxNumberOfApples(weight: number[]): number {
    weight.sort((a, b) => a - b);
    let s = 0;
    for (let i = 0; i < weight.length; ++i) {
        s += weight[i];
        if (s > 5000) {
            return i;
        }
    }
    return weight.length;
}
