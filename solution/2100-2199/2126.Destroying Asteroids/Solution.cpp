class Solution {
public:
    bool asteroidsDestroyed(int mass, vector<int>& asteroids) {
        sort(asteroids.begin(), asteroids.end());
        long long m = mass;
        for (int v : asteroids) {
            if (m < v) return false;
            m += v;
        }
        return true;
    }
};