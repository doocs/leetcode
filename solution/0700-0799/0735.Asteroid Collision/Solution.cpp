class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> stk;
        for (int x : asteroids) {
            if (x > 0) {
                stk.push_back(x);
            } else {
                while (stk.size() && stk.back() > 0 && stk.back() < -x) {
                    stk.pop_back();
                }
                if (stk.size() && stk.back() == -x) {
                    stk.pop_back();
                } else if (stk.empty() || stk.back() < 0) {
                    stk.push_back(x);
                }
            }
        }
        return stk;
    }
};