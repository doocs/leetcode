function countDigits(num: number): number {
    let ans = 0;
    let cur = num;
    while (cur !== 0) {
        if (num % (cur % 10) === 0) {
            ans++;
        }
        cur = Math.floor(cur / 10);
    }
    return ans;
}
