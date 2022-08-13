class Solution {
public:
    vector<int> pancakeSort(vector<int>& arr) {
        int n = arr.size();
        vector<int> ans;
        for (int i = n - 1; i > 0; --i) {
            int j = i;
            for (; j > 0 && arr[j] != i + 1; --j)
                ;
            if (j == i) continue;
            if (j > 0) {
                ans.push_back(j + 1);
                reverse(arr.begin(), arr.begin() + j + 1);
            }
            ans.push_back(i + 1);
            reverse(arr.begin(), arr.begin() + i + 1);
        }
        return ans;
    }
};