function makeFancyString(s) {
    let [n, ans] = [s.length, ''];
    for (let i = 0; i < n; i++) {
        if (s[i] !== s[i - 1] || s[i] !== s[i - 2]) {
            ans += s[i];
        }
    }
    return ans;
}
