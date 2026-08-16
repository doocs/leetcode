class Solution {
public:
    int maximumGap(string skill, string station) {
        int n = skill.size();
        int m = station.size();

        vector<int> suf(n);
        int j = m - 1;

        for (int i = n - 1; i > 0; i--) {
            while (station[j] != skill[i]) {
                j--;
            }

            suf[i] = j;
            j--;
        }

        int ans = 0;
        int pre = 0;

        for (int i = 0; i < n - 1; i++) {
            while (station[pre] != skill[i]) {
                pre++;
            }

            ans = max(ans, suf[i + 1] - pre);
            pre++;
        }

        return ans;
    }
};