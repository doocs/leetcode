class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        unordered_set<int> s{{0}};
        unordered_set<int> ans;
        for (int& x : arr) {
            unordered_set<int> t{{x}};
            for (int y : s) {
                t.insert(x | y);
            }
            s = move(t);
            ans.insert(s.begin(), s.end());
        }
        return ans.size();
    }
};