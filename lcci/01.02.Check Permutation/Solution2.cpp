class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        ranges::sort(s1);
        ranges::sort(s2);
        return s1 == s2;
    }
};
