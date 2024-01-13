class Solution {
public:
    bool canWin(string currentState) {
        int n = currentState.size();
        vector<int> sg(n + 1, -1);
        sg[0] = 0, sg[1] = 0;

        function<int(int)> win = [&](int i) {
            if (sg[i] != -1) return sg[i];
            vector<bool> vis(n);
            for (int j = 0; j < i - 1; ++j) vis[win(j) ^ win(i - j - 2)] = true;
            for (int j = 0; j < n; ++j)
                if (!vis[j]) return sg[i] = j;
            return 0;
        };

        int ans = 0, i = 0;
        while (i < n) {
            int j = i;
            while (j < n && currentState[j] == '+') ++j;
            ans ^= win(j - i);
            i = j + 1;
        }
        return ans > 0;
    }
};