function minNumberOfHours(
    initialEnergy: number,
    initialExperience: number,
    energy: number[],
    experience: number[],
): number {
    const n = energy.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        const minEnergy = energy[i];
        const minExperience = experience[i];
        if (initialEnergy <= minEnergy) {
            const need = minEnergy - initialEnergy + 1;
            ans += need;
            initialEnergy += need;
        }
        if (initialExperience <= minExperience) {
            const need = minExperience - initialExperience + 1;
            ans += need;
            initialExperience += need;
        }
        initialEnergy -= minEnergy;
        initialExperience += minExperience;
    }
    return ans;
}
