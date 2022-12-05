function dividePlayers(skill: number[]): number {
    const n = skill.length;
    skill.sort((a, b) => a - b);
    const target = skill[0] + skill[n - 1];
    let ans = 0;
    for (let i = 0; i < n >> 1; i++) {
        if (target !== skill[i] + skill[n - 1 - i]) {
            return -1;
        }
        ans += skill[i] * skill[n - 1 - i];
    }
    return ans;
}
