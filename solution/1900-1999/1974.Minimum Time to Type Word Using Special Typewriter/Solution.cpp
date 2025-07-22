class Solution {
public:
    int minTimeToType(string word) {
        int ans = word.length();
        char a = 'a';
        for (char c : word) {
            int d = abs(a - c);
            ans += min(d, 26 - d);
            a = c;
        }
        return ans;
    }
};
