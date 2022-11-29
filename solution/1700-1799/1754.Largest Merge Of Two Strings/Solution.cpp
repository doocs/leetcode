class Solution {
public:
    string largestMerge(string word1, string word2) {
        int m = word1.size(), n = word2.size();
        int i = 0, j = 0;
        string ans;
        while (i < m && j < n) {
            bool gt = word1.substr(i) > word2.substr(j);
            ans += gt ? word1[i++] : word2[j++];
        }
        ans += word1.substr(i);
        ans += word2.substr(j);
        return ans;
    }
};