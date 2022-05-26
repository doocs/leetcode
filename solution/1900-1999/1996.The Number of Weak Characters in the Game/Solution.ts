function numberOfWeakCharacters(properties: number[][]): number {
    properties.sort((a, b) => (a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]));

    let ans = 0;
    let max = 0;
    for (let [, b] of properties) {
        if (b < max) {
            ans++;
        } else {
            max = b;
        }
    }
    return ans;
}
