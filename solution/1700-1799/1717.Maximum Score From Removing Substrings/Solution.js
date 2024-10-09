function maximumGain(s, x, y) {
    let [a, b] = ['a', 'b'];
    if (x < y) {
        [x, y] = [y, x];
        [a, b] = [b, a];
    }

    let [ans, cnt1, cnt2] = [0, 0, 0];
    for (let c of s) {
        if (c === a) {
            cnt1++;
        } else if (c === b) {
            if (cnt1) {
                ans += x;
                cnt1--;
            } else {
                cnt2++;
            }
        } else {
            ans += Math.min(cnt1, cnt2) * y;
            cnt1 = 0;
            cnt2 = 0;
        }
    }
    ans += Math.min(cnt1, cnt2) * y;
    return ans;
}
