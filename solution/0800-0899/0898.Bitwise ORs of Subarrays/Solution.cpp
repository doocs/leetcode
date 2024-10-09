class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        unordered_set<int> ans;
        unordered_set<int> s;
        for (int x : arr) {
            unordered_set<int> t;
            for (int y : s) {
                t.insert(x | y);
            }
            t.insert(x);
            ans.insert(t.begin(), t.end());
            s = move(t);
        }
        return ans.size();
    }
};