function countDigits(num: number): number {
    let ans = 0;
    for (const s of num.toString()) {
        if (num % Number(s) === 0) {
            ans++;
        }
    }
    return ans;
}
