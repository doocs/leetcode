class Solution {
public:
    int minimumDeletions(string s) {
        int lb = 0, ra = count(s.begin(), s.end(), 'a');
        int ans = ra;
        for (char& c : s) {
            ra -= c == 'a';
            ans = min(ans, lb + ra);
            lb += c == 'b';
        }
        return ans;
    }
};