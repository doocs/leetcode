class Solution {
public:
    vector<int> findSwapValues(vector<int>& array1, vector<int>& array2) {
        long long s1 = accumulate(array1.begin(), array1.end(), 0LL);
        long long s2 = accumulate(array2.begin(), array2.end(), 0LL);
        long long diff = s1 - s2;
        if (diff & 1) {
            return {};
        }
        diff >>= 1;
        unordered_set<int> s(array2.begin(), array2.end());
        for (int x : array1) {
            int y = x - diff;
            if (s.count(y)) {
                return {x, y};
            }
        }
        return {};
    }
};