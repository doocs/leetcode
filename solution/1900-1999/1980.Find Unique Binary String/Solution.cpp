class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        auto s = count(nums);
        for (int i = 0, n = nums.size(); i < n + 1; ++i) {
            if (!s.count(i))
                return repeat("1", i) + repeat("0", n - i);
        }
        return "";
    }

    unordered_set<int> count(vector<string>& nums) {
        unordered_set<int> s;
        for (auto& num : nums) {
            int t = 0;
            for (char c : num) {
                if (c == '1')
                    ++t;
            }
            s.insert(t);
        }
        return s;
    }

    string repeat(string s, int n) {
        string res = "";
        for (int i = 0; i < n; ++i) {
            res += s;
        }
        return res;
    }
};