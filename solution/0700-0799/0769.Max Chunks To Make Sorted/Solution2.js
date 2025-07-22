function maxChunksToSorted(arr) {
    const stk = [];

    for (const x of arr) {
        if (stk.at(-1) > x) {
            const top = stk.pop();
            while (stk.length && stk.at(-1) > x) stk.pop();
            stk.push(top);
        } else stk.push(x);
    }

    return stk.length;
}
