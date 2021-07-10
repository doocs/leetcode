class Solution {
public:
    int minPartitions(string n) {
        int res = 0;
        for (auto& c : n) {
            res = max(res, c - '0');
        }
        return res;
    }
};