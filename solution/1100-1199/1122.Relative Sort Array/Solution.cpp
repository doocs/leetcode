class Solution {
public:
    vector<int> relativeSortArray(vector<int>& arr1, vector<int>& arr2) {
        vector<int> mp(1001);
        for (int x : arr1) ++mp[x];
        int i = 0;
        for (int x : arr2) {
            while (mp[x]-- > 0) arr1[i++] = x;
        }
        for (int j = 0; j < mp.size(); ++j) {
            while (mp[j]-- > 0) arr1[i++] = j;
        }
        return arr1;
    }
};