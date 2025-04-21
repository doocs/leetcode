function numRabbits(answers) {
    const cnt = {};
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
