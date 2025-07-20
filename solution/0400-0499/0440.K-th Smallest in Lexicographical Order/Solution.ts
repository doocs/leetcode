function findKthNumber(n: number, k: number): number {
    function count(curr: number): number {
        let next = curr + 1;
        let cnt = 0;
        while (curr <= n) {
            cnt += Math.min(n - curr + 1, next - curr);
            curr *= 10;
            next *= 10;
        }
        return cnt;
    }

    let curr = 1;
    k--;

    while (k > 0) {
        const cnt = count(curr);
        if (k >= cnt) {
            k -= cnt;
            curr += 1;
        } else {
            k -= 1;
            curr *= 10;
        }
    }

    return curr;
}
