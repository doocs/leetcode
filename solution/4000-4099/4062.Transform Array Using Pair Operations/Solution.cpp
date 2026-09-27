class Solution {
public:
    bool canTransform(vector<int>& source, vector<int>& target) {
        long long s = 0;
        for (int x : source) {
            s += x;
        }
        for (int x : target) {
            s -= x;
        }
        return s == 0;
    }
};
