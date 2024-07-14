function findWinningPlayer(skills: number[], k: number): number {
    const n = skills.length;
    k = Math.min(k, n - 1);
    let [i, cnt] = [0, 0];
    for (let j = 1; j < n; ++j) {
        if (skills[i] < skills[j]) {
            i = j;
            cnt = 1;
        } else {
            ++cnt;
        }
        if (cnt === k) {
            break;
        }
    }
    return i;
}
