class Solution {
public:
    string getHint(string secret, string guess) {
        int x = 0, y = 0;
        vector<int> cnt1(10);
        vector<int> cnt2(10);
        for (int i = 0; i < secret.size(); ++i) {
            int a = secret[i] - '0', b = guess[i] - '0';
            if (a == b)
                ++x;
            else {
                ++cnt1[a];
                ++cnt2[b];
            }
        }
        for (int i = 0; i < 10; ++i) y += min(cnt1[i], cnt2[i]);
        return to_string(x) + "A" + to_string(y) + "B";
    }
};