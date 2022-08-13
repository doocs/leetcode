class Solution {
public:
    int convertTime(string current, string correct) {
        int a = stoi(current.substr(0, 2)) * 60 + stoi(current.substr(3, 2));
        int b = stoi(correct.substr(0, 2)) * 60 + stoi(correct.substr(3, 2));
        int ans = 0, d = b - a;
        vector<int> inc = {60, 15, 5, 1};
        for (int i : inc) {
            ans += d / i;
            d %= i;
        }
        return ans;
    }
};