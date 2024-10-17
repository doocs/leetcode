function maximumSwap(num) {
    const ans = [...String(num)];
    let [min, max, maybeMax, n] = [-1, -1, -1, ans.length];

    for (let i = n - 1; i >= 0; i--) {
        if (ans[i] > (ans[maybeMax] ?? -1)) maybeMax = i;
        if (i < maybeMax && ans[i] < ans[maybeMax]) {
            [min, max] = [i, maybeMax];
        }
    }

    if (~min && ~max && min < max) {
        [ans[min], ans[max]] = [ans[max], ans[min]];
    }

    return +ans.join('');
}
