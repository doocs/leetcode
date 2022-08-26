class Solution {
public:
    int countElements(vector<int>& arr) {
        unordered_set<int> s(arr.begin(), arr.end());
        int ans = 0;
        for (int x : arr) {
            ans += s.count(x + 1);
        }
        return ans;
    }
};