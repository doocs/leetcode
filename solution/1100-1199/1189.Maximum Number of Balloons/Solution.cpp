class Solution {
public:
    int maxNumberOfBalloons(string text) {
        vector<int> counter(26);
        for (char& c : text) ++counter[c - 'a'];
        counter['l' - 'a'] >>= 1;
        counter['o' - 'a'] >>= 1;
        int ans = 10000;
        string t = "balon";
        for (char& c : t) ans = min(ans, counter[c - 'a']);
        return ans;
    }
};