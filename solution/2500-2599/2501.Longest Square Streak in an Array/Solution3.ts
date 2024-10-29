function longestSquareStreak(nums: number[]): number {
    const cnt: Record<number, number> = {};
    const squares = new Set<number>();

    for (const x of new Set(nums)) {
        cnt[x] = (cnt[x] ?? -1) + 1;
        cnt[x ** 2] = (cnt[x ** 2] ?? -1) + 1;
    }

    for (const key in cnt) {
        const x = +key;
        if (cnt[x] || cnt[x ** 2]) {
            squares.add(x);
        }
    }

    if (squares.size <= 1) return -1;

    const iterator = squares[Symbol.iterator]();
    let [max, c, x] = [0, 0, iterator.next().value];

    while (x !== undefined) {
        if (squares.has(x)) {
            squares.delete(x);
            x **= 2;
            c++;
        } else {
            max = Math.max(max, c);
            x = iterator.next().value;
            c = 0;
        }
    }

    return max;
}
