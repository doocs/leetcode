class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_map<int, int> m;
        int n = arr.size();
        for (int i = 0; i < n; ++i) m[arr[i]] = i;
        for (int i = 0; i < n; ++i)
            if (m.count(arr[i] * 2) && m[arr[i] * 2] != i)
                return true;
        return false;
    }
};