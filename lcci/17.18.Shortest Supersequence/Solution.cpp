class Solution {
public:
    vector<int> shortestSeq(vector<int>& big, vector<int>& small) {
        int cnt = small.size();
        unordered_map<int, int> need;
        unordered_map<int, int> window;
        for (int x : small) {
            need[x] = 1;
        }
        int k = -1, mi = 1 << 30;
        for (int i = 0, j = 0; i < big.size(); ++i) {
            window[big[i]]++;
            if (need[big[i]] >= window[big[i]]) {
                --cnt;
            }
            while (cnt == 0) {
                if (i - j + 1 < mi) {
                    mi = i - j + 1;
                    k = j;
                }
                if (need[big[j]] >= window[big[j]]) {
                    ++cnt;
                }
                window[big[j++]]--;
            }
        }
        if (k < 0) {
            return {};
        }
        return {k, k + mi - 1};
    }
};