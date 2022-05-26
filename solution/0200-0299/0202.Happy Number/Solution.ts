function isHappy(n: number): boolean {
    const getNext = (n: number) => {
        let res = 0;
        while (n !== 0) {
            res += (n % 10) ** 2;
            n = Math.floor(n / 10);
        }
        return res;
    };

    let slow = n;
    let fast = getNext(n);
    while (slow !== fast) {
        slow = getNext(slow);
        fast = getNext(getNext(fast));
    }
    return fast === 1;
}
