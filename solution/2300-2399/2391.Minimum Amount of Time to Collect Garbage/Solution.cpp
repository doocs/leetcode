class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        int ans = 0;
        vector<int> pos(26);
        for (int i = 0; i < garbage.size(); ++i) {
            ans += garbage[i].size();
            for (char c : garbage[i]) {
                pos[c - 'A'] = i;
            }
        }
        vector<int> s(travel.size() + 1);
        for (int i = 0; i < travel.size(); ++i) {
            s[i + 1] = s[i] + travel[i];
        }
        for (int i : pos) {
            ans += s[i];
        }
        return ans;
    }
};