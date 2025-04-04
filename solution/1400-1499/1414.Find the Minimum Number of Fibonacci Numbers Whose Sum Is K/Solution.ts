function findMinFibonacciNumbers(k: number): number {
    let [a, b] = [1, 1];
    while (b <= k) {
        let c = a + b;
        a = b;
        b = c;
    }

    let ans = 0;
    while (k > 0) {
        if (k >= b) {
            k -= b;
            ans++;
        }
        let c = b - a;
        b = a;
        a = c;
    }
    return ans;
}
