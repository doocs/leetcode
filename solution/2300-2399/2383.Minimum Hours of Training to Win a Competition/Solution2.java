class Solution {
    public int minNumberOfHours(
        int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int s = 0;
        for (int x : energy) {
            s += x;
        }
        int ans = Math.max(0, s - initialEnergy + 1);
        for (int x : experience) {
            if (initialExperience <= x) {
                ans += x - initialExperience + 1;
                initialExperience = x + 1;
            }
            initialExperience += x;
        }
        return ans;
    }
}