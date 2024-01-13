function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    const s = energy.reduce((a, b) => a + b, 0);
    let ans = Math.max(0, s - initialEnergy + 1);
    for (const x of experience) {
        if (initialExperience <= x) {
            ans += x - initialExperience + 1;
            initialExperience = x + 1;
        }
        initialExperience += x;
    }
    return ans;
}
