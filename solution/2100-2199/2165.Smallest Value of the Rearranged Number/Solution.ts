function smallestNumber(num: number): number {
    const neg = num < 0;
    num = Math.abs(num);
    const cnt = Array(10).fill(0);

    while (num > 0) {
        cnt[num % 10]++;
        num = Math.floor(num / 10);
    }

    let ans = 0;
    if (neg) {
        for (let i = 9; i >= 0; i--) {
            while (cnt[i] > 0) {
                ans = ans * 10 + i;
                cnt[i]--;
            }
        }
        return -ans;
    }

    if (cnt[0] > 0) {
        for (let i = 1; i < 10; i++) {
            if (cnt[i] > 0) {
                cnt[i]--;
                ans = i;
                break;
            }
        }
    }

    for (let i = 0; i < 10; i++) {
        while (cnt[i] > 0) {
            ans = ans * 10 + i;
            cnt[i]--;
        }
    }

    return ans;
}
