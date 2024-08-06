function kthDistinct(arr k) {
    const cnt = new Map();

    for (const x of arr) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    for (const [x, c] of cnt) {
        if (c === 1) k--;
        if (!k) return x;
    }

    return '';
}
