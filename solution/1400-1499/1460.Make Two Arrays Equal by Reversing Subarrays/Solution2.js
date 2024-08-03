function canBeEqual(target, arr) {
    const n = target.length;
    const cnt = Array(1001).fill(0);
    for (let i = 0; i < n; i++) {
        cnt[target[i]]++;
        cnt[arr[i]]--;
    }
    return cnt.every(v => !v);
}
