function maximumSwap(num: number): number {
    const list = new Array();
    while (num !== 0) {
        list.push(num % 10);
        num = Math.floor(num / 10);
    }
    const n = list.length;
    const idx = new Array();
    for (let i = 0, j = 0; i < n; i++) {
        if (list[i] > list[j]) {
            j = i;
        }
        idx.push(j);
    }
    for (let i = n - 1; i >= 0; i--) {
        if (list[idx[i]] !== list[i]) {
            [list[idx[i]], list[i]] = [list[i], list[idx[i]]];
            break;
        }
    }
    let res = 0;
    for (let i = n - 1; i >= 0; i--) {
        res = res * 10 + list[i];
    }
    return res;
}
