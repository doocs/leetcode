class Solution {
public:
    int mostWordsFound(vector<string>& sentences) {
        int ans = 0;
        for (auto& s : sentences) {
            int cnt = 1 + count(s.begin(), s.end(), ' ');
            ans = max(ans, cnt);
        }
        return ans;
    }
};