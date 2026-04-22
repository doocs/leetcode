function maxDistance(colors: number[]): number {
    const n = colors.length;
    if (colors[0] !== colors[n - 1]) {
        return n - 1;
    }
    let [i, j] = [1, n - 2];
    while (colors[i] === colors[0]) {
        i++;
    }
    while (colors[j] === colors[0]) {
        j--;
    }
    return Math.max(n - i - 1, j);
}
