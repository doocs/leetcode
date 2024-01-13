class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k != 0) return false;
        map<int, int> mp;
        for (int& h : nums) mp[h] += 1;
        while (!mp.empty()) {
            int v = mp.begin()->first;
            for (int i = v; i < v + k; ++i) {
                if (!mp.count(i)) return false;
                if (mp[i] == 1)
                    mp.erase(i);
                else
                    mp[i] -= 1;
            }
        }
        return true;
    }
};