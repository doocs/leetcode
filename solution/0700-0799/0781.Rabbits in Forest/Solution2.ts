function numRabbits(answers: number[]): number {
    const cnt: Record<number, number> = {};
    let ans = 0;

    for (const x of answers) {
        if (cnt[x]) {
            cnt[x]--;
        } else {
            cnt[x] = x;
            ans += x + 1;
        }
    }

    return ans;
}
