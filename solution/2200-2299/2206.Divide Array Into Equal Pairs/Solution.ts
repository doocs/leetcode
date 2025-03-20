function divideArray(nums: number[]): boolean {
    const cnt = Array(501).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    for (const x of cnt) {
        if (x & 1) return false;
    }

    return true;
}
