class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        unordered_set<int> s;
        int prev = 0;
        for (int i = 0; i < arr.size(); ++i) {
            prev |= arr[i];
            int curr = 0;
            for (int j = i; ~j; --j) {
                curr |= arr[j];
                s.insert(curr);
                if (curr == prev) break;
            }
        }
        return s.size();
    }
};