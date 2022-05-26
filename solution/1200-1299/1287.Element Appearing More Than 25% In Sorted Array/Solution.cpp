class Solution {
public:
    int findSpecialInteger(vector<int>& arr) {
        int n = arr.size();
        for (int i = 0; i < n; ++i)
            if (arr[i] == arr[i + (n >> 2)]) return arr[i];
        return 0;
    }
};