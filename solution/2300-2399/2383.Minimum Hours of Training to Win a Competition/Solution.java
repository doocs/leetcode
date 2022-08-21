class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int ans = 0;
        for (int i = 0; i < energy.length; ++i) {
            int a = energy[i], b = experience[i];
            if (initialEnergy <= a) {
                int t = a - initialEnergy + 1;
                ans += t;
                initialEnergy += t;
            }
            if (initialExperience <= b) {
                int t = b - initialExperience + 1;
                ans += t;
                initialExperience += t;
            }
            initialEnergy -= a;
            initialExperience += b;
        }
        return ans;
    }
}