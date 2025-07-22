class Solution {
public:
    bool asteroidsDestroyed(int mass, vector<int>& asteroids) {
        ranges::sort(asteroids);
        long long m = mass;
        for (int x : asteroids) {
            if (m < x) {
                return false;
            }
            m += x;
        }
        return true;
    }
};
