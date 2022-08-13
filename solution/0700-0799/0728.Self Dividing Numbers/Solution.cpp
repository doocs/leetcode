class Solution {
public:
    vector<int> selfDividingNumbers(int left, int right) {
        vector<int> ans;
        for (int i = left; i <= right; ++i)
            if (check(i))
                ans.push_back(i);
        return ans;
    }

    bool check(int num) {
        for (int t = num; t; t /= 10) {
            int x = t % 10;
            if (x == 0 || num % x) return false;
        }
        return true;
    }
};