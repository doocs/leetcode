function numberOfWeakCharacters(properties: number[][]): number {
    properties.sort((a, b) => (a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]));
    let ans = 0;
    let mx = 0;
    for (const [, x] of properties) {
        if (x < mx) {
            ans++;
        } else {
            mx = x;
        }
    }
    return ans;
}
