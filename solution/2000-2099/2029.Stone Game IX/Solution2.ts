function stoneGameIX(stones: number[]): boolean {
    if (stones.length === 1) return false;

    const cnt = Array(3).fill(0);
    for (const x of stones) cnt[x % 3]++;

    const check = (x: number, cnt: number[]): boolean => {
        let c = 1;
        if (--cnt[x] < 0) return false;

        while (cnt[1] || cnt[2]) {
            if (cnt[x]) {
                cnt[x]--;
                x = x === 1 ? 2 : 1;
            } else return (c + cnt[0]) % 2 === 1;
            c++;
        }

        return false;
    };

    return check(1, [...cnt]) || check(2, [...cnt]);
}
