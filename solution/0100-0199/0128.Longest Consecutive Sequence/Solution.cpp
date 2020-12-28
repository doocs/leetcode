class Solution {
public:
    int longestConsecutive(vector<int>& nums) {
        int res = 0;
        unordered_set<int> set{nums.begin(), nums.end()};
        for (int num : nums) {
            if (!set.count(num)) continue;
            set.erase(num);
            int pre = num - 1, next = num + 1;
            while (set.count(pre)) set.erase(pre--);
            while (set.count(next)) set.erase(next++);
            res = max(res, next - pre - 1);
        }
        return res;
    }
};
