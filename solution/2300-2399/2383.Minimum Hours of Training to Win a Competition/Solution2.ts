function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    let res = 0;
    for (const v of experience) {
        if (initialExperience <= v) {
            res += v - initialExperience + 1;
            initialExperience = v + 1;
        }
        initialExperience += v;
    }
    for (const v of energy) {
        if (initialEnergy <= v) {
            res += v - initialEnergy + 1;
            initialEnergy = v + 1;
        }
        initialEnergy -= v;
    }
    return res;
}
