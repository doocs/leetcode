int minNumberOfHours(int initialEnergy, int initialExperience, int* energy, int energySize, int* experience, int experienceSize) {
    int res = 0;
    for (int i = 0; i < energySize; i++) {
        if (initialEnergy <= energy[i]) {
            res += energy[i] - initialEnergy + 1;
            initialEnergy = energy[i] + 1;
        }
        if (initialExperience <= experience[i]) {
            res += experience[i] - initialExperience + 1;
            initialExperience = experience[i] + 1;
        }
        initialEnergy -= energy[i];
        initialExperience += experience[i];
    }
    return res;
}
