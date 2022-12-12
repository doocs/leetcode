class Solution {
public:
    vector<int> masterMind(string solution, string guess) {
        int x = 0, y = 0;
        unordered_map<char, int> cnt1;
        unordered_map<char, int> cnt2;
        for (int i = 0; i < 4; ++i) {
            x += solution[i] == guess[i];
            cnt1[solution[i]]++;
            cnt2[guess[i]]++;
        }
        for (char c : "RYGB") y += min(cnt1[c], cnt2[c]);
        return vector<int>{x, y - x};
    }
};