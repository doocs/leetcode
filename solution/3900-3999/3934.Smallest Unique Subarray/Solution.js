var smallestUniqueSubarray = function (nums) {
    const n = nums.length;
    const key = (a, b) => a * 2 ** 17 + b; // a pair of names as one exact number: every name is below 2^17
    const names = [nums]; // names[k][i] is equal for equal windows of length 2^k starting at i
    for (let half = 1; 2 * half <= n; half *= 2) {
        // blocks of 2, 4, 8, ... while they fit
        const ids = new Map(); // pair of halves -> number of the whole window
        const block = names.at(-1),
            whole = [];
        for (let i = half; i < block.length; i++) {
            const pair = key(block[i - half], block[i]);
            if (!ids.has(pair)) ids.set(pair, ids.size);
            whole.push(ids.get(pair));
        }
        names.push(whole);
    }

    const found = length => {
        // is some window of this length unique?
        const k = 31 - Math.clz32(length); // its first 2^k elements and its last 2^k cover it
        const block = names[k],
            shift = length - 2 ** k,
            count = new Map();
        for (let i = shift; i < block.length; i++) {
            const pair = key(block[i - shift], block[i]);
            count.set(pair, (count.get(pair) ?? 0) + 1);
        }
        return [...count.values()].includes(1);
    };

    let lo = 1,
        hi = n; // the first length that works is in lo..hi
    while (lo < hi) {
        const mid = (lo + hi) >> 1;
        if (found(mid)) hi = mid;
        else lo = mid + 1;
    }
    return lo;
};
