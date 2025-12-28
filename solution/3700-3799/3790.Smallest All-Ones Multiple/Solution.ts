function minAllOneMultiple(k: number): number {
    if ((k & 1) === 0) {
        return -1;
    }

    let x = 1 % k;
    let ans = 1;

    for (let i = 0; i < k; i++) {
        x = (x * 10 + 1) % k;
        ans++;
        if (x === 0) {
            return ans;
        }
    }

    return -1;
}
