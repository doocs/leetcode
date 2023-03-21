class Solution {
public:
    vector<string> findLongestSubarray(vector<string>& array) {
        unordered_map<int, int> vis{{0, -1}};
        int s = 0, mx = 0, k = 0;
        for (int i = 0; i < array.size(); ++i) {
            s += array[i][0] >= 'A' ? 1 : -1;
            if (vis.count(s)) {
                int j = vis[s];
                if (mx < i - j) {
                    mx = i - j;
                    k = j + 1;
                }
            } else {
                vis[s] = i;
            }
        }
        return vector<string>(array.begin() + k, array.begin() + k + mx);
    }
};