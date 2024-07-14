function minAddToMakeValid(s: string): number {
    let [ans, cnt] = [0, 0];
    for (const c of s) {
        if (c === '(') {
            ++cnt;
        } else if (cnt) {
            --cnt;
        } else {
            ++ans;
        }
    }
    ans += cnt;
    return ans;
}
