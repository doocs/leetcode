class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        int N = 1001;
        vector<int> c1(N);
        vector<int> c2(N);
        for (auto& e : trust) {
            ++c1[e[0]];
            ++c2[e[1]];
        }
        for (int i = 1; i < N; ++i) {
            if (c1[i] == 0 && c2[i] == n - 1) return i;
        }
        return -1;
    }
};