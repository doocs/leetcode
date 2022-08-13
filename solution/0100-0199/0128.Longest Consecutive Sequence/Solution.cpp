class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        unordered_set<int> s;
        for (int num : nums)
            s.insert(num);
        int res = 0;
        for (int num : nums) {
            if (!s.count(num - 1)) {
                int t = 1, next = num + 1;
                while (s.count(next++))
                    ++t;
                res = max(res, t);
            }
        }
        return res;
    }
};