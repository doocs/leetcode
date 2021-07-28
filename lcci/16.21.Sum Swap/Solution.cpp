class Solution {
public:
    vector<int> findSwapValues(vector<int>& array1, vector<int>& array2) {
        int s1 = 0, s2 = 0;
        unordered_set<int> s;
        for (int a : array1) s1 += a;
        for (int b : array2) {
            s2 += b;
            s.insert(b);
        }
        int diff = s1 - s2;
        if (diff & 1) {
            return {};
        }
        diff >>= 1;
        for (int a : array1) {
            int b = a - diff;
            if (s.count(b)) {
                return {a, b};
            }
        }
        return {};
    }
};