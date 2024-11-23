class Solution {
public:
    int largestInteger(int num) {
        string s = to_string(num);
        int cnt[10] = {0};
        for (char c : s) {
            cnt[c - '0']++;
        }
        int idx[2] = {8, 9};
        int ans = 0;
        for (char c : s) {
            int x = c - '0';
            while (cnt[idx[x & 1]] == 0) {
                idx[x & 1] -= 2;
            }
            ans = ans * 10 + idx[x & 1];
            cnt[idx[x & 1]]--;
        }
        return ans;
    }
};
