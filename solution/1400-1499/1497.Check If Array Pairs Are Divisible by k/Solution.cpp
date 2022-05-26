class Solution {
public:
    bool canArrange(vector<int>& arr, int k) {
        vector<int> mod(k);
        for (int v : arr) ++mod[(v % k + k) % k];
        for (int i = 1; i < k; ++i)
            if (mod[i] != mod[k - i])
                return false;
        return mod[0] % 2 == 0;
    }
};