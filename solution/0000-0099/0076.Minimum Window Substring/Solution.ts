function minWindow(s: string, t: string): string {
    let n1 = s.length,
        n2 = t.length;
    if (n1 < n2) return '';
    let need = new Array(128).fill(0);
    let window = new Array(128).fill(0);
    for (let i = 0; i < n2; ++i) {
        ++need[t.charCodeAt(i)];
    }

    let left = 0,
        right = 0;
    let res = '';
    let count = 0;
    let min = n1 + 1;
    while (right < n1) {
        let cur = s.charCodeAt(right);
        ++window[cur];
        if (need[cur] > 0 && need[cur] >= window[cur]) {
            ++count;
        }
        while (count == n2) {
            cur = s.charCodeAt(left);
            if (need[cur] > 0 && need[cur] >= window[cur]) {
                --count;
            }
            if (right - left + 1 < min) {
                min = right - left + 1;
                res = s.slice(left, right + 1);
            }
            --window[cur];
            ++left;
        }
        ++right;
    }
    return res;
}
