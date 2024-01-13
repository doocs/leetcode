class Solution {
public:
    int minNumberOfHours(int initialEnergy, int initialExperience, vector<int>& energy, vector<int>& experience) {
        int s = accumulate(energy.begin(), energy.end(), 0);
        int ans = max(0, s - initialEnergy + 1);
        for (int x : experience) {
            if (initialExperience <= x) {
                ans += x - initialExperience + 1;
                initialExperience = x + 1;
            }
            initialExperience += x;
        }
        return ans;
    }
};