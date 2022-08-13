class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& digits) {
        vector<int> counter = count(digits);
        vector<int> ans;
        for (int i = 100; i < 1000; i += 2) {
            vector<int> t(3);
            for (int j = 0, k = i; k > 0; ++j) {
                t[j] = k % 10;
                k /= 10;
            }
            vector<int> cnt = count(t);
            if (check(counter, cnt)) ans.push_back(i);
        }
        return ans;
    }

    vector<int> count(vector<int>& nums) {
        vector<int> counter(10);
        for (int num : nums) ++counter[num];
        return counter;
    }

    bool check(vector<int>& cnt1, vector<int>& cnt2) {
        for (int i = 0; i < 10; ++i)
            if (cnt1[i] < cnt2[i])
                return false;
        return true;
    }
};