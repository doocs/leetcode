function maximumTastiness(price: number[], k: number): number {
    price.sort((a, b) => a - b);
    let l = 0;
    let r = price[price.length - 1] - price[0];
    const check = (x: number): boolean => {
        let [cnt, pre] = [0, -x];
        for (const cur of price) {
            if (cur - pre >= x) {
                pre = cur;
                ++cnt;
            }
        }
        return cnt >= k;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
