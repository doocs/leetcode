class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        unordered_map<char, int> last;
        int ans = 0;
        for (int i = 0; i < garbage.size(); ++i) {
            auto& s = garbage[i];
            ans += s.size();
            for (char& c : s) {
                last[c] = i;
            }
        }
        int ts = 0;
        for (int i = 1; i <= travel.size(); ++i) {
            ts += travel[i - 1];
            for (auto& [_, j] : last) {
                if (i == j) {
                    ans += ts;
                }
            }
        }
        return ans;
    }
};