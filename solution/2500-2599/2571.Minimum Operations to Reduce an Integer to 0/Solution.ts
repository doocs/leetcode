function minOperations(n: number): number {
    let [ans, cnt] = [0, 0];
    for (; n; n >>= 1) {
        if (n & 1) {
            ++cnt;
        } else if (cnt) {
            ++ans;
            cnt = cnt === 1 ? 0 : 1;
        }
    }
    if (cnt === 1) {
        ++ans;
    } else if (cnt > 1) {
        ans += 2;
    }
    return ans;
}
