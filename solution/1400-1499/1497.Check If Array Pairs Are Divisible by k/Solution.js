function canArrange(arr, k) {
    const cnt = Array(k).fill(0);

    for (const x of arr) {
        cnt[((x % k) + k) % k]++;
    }

    for (let i = 1; i < k; i++) {
        if (cnt[i] !== cnt[k - i]) return false;
    }

    return cnt[0] % 2 === 0;
}
