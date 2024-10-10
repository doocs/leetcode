class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k) {
            return false;
        }
        map<int, int> mp;
        for (int& h : nums) {
            mp[h] += 1;
        }
        while (!mp.empty()) {
            int v = mp.begin()->first;
            for (int i = v; i < v + k; ++i) {
                if (!mp.contains(i)) {
                    return false;
                }
                if (--mp[i] == 0) {
                    mp.erase(i);
                }
            }
        }
        return true;
    }
};
