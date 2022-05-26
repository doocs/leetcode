class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> ans;
        for (int a : asteroids) {
            if (a > 0) {
                ans.push_back(a);
            } else {
                while (!ans.empty() && ans.back() > 0 && ans.back() < -a) {
                    ans.pop_back();
                }
                if (!ans.empty() && ans.back() == -a) {
                    ans.pop_back();
                } else if (ans.empty() || ans.back() < -a) {
                    ans.push_back(a);
                }
            }
        }
        return ans;
    }
};
