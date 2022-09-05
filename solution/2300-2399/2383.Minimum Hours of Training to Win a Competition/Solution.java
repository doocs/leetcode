class Solution {
    public int minNumberOfHours(
        int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int a = energy[i], b = experience[i];
            if (initialEnergy <= a) {
                ans += a - initialEnergy + 1;
                initialEnergy = a + 1;
            }
            if (initialExperience <= b) {
                ans += b - initialExperience + 1;
                initialExperience = b + 1;
            }
            initialEnergy -= a;
            initialExperience += b;
        }
        return ans;
    }
}