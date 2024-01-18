function isHappy(n: number): boolean {
    const getNext = (n: number) => {
        let res = 0;
        while (n !== 0) {
            res += (n % 10) ** 2;
            n = Math.floor(n / 10);
        }
        return res;
    };
    const set = new Set();
    while (n !== 1) {
        const next = getNext(n);
        if (set.has(next)) {
            return false;
        }
        set.add(next);
        n = next;
    }
    return true;
}
