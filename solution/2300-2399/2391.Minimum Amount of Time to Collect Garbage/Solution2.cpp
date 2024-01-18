class Solution {
public:
    int garbageCollection(vector<string>& garbage, vector<int>& travel) {
        auto f = [&](char x) {
            int ans = 0, st = 0;
            for (int i = 0; i < garbage.size(); ++i) {
                int cnt = 0;
                for (char& c : garbage[i]) {
                    if (c == x) {
                        ++cnt;
                    }
                }
                if (cnt > 0) {
                    ans += cnt + st;
                    st = 0;
                }
                if (i < travel.size()) {
                    st += travel[i];
                }
            }
            return ans;
        };
        return f('M') + f('P') + f('G');
    }
};