function stoneGameIX(stones) {
    const c1 = Array(3).fill(0);
    for (const x of stones) {
        ++c1[x % 3];
    }
    const c2 = [c1[0], c1[2], c1[1]];
    const check = cnt => {
        if (--cnt[1] < 0) {
            return false;
        }
        let r = 1 + Math.min(cnt[1], cnt[2]) * 2 + cnt[0];
        if (cnt[1] > cnt[2]) {
            --cnt[1];
            ++r;
        }
        return r % 2 === 1 && cnt[1] !== cnt[2];
    };
    return check(c1) || check(c2);
}
