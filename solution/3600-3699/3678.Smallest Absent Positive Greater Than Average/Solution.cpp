class Solution {
public:
    int smallestAbsent(vector<int>& nums) {
        unordered_set<int> s;
        int sum = 0;
        for (int x : nums) {
            s.insert(x);
            sum += x;
        }
        int ans = max(1, sum / (int) nums.size() + 1);
        while (s.contains(ans)) {
            ++ans;
        }
        return ans;
    }
};
