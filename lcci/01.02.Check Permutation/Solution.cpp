class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        int n1 = s1.size();
        int n2 = s2.size();
        if (n1 != n2) return 0;
        vector<int> counter(128);
        for (int i = 0; i < n1; ++i) {
            ++counter[s1[i]];
            --counter[s2[i]];
        }
        for (int v : counter)
            if (v) return 0;
        return 1;
    }
};