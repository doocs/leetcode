const romanToInt = function (s) {
    const d = {
        I: 1,
        V: 5,
        X: 10,
        L: 50,
        C: 100,
        D: 500,
        M: 1000,
    };
    let ans = 0;
    for (let i = 0; i < s.length; ++i) {
        if (d[s[i]] < d[s[i + 1]]) {
            ans -= d[s[i]];
        } else {
            ans += d[s[i]];
        }
    }
    return ans;
};
