class Solution {
public:
    string pushDominoes(string dominoes) {
        int n = dominoes.size();
        queue<int> q;
        vector<int> time(n, -1);
        vector<string> force(n);
        for (int i = 0; i < n; i++) {
            if (dominoes[i] == '.') continue;
            q.emplace(i);
            time[i] = 0;
            force[i].push_back(dominoes[i]);
        }

        string ans(n, '.');
        while (!q.empty()) {
            int i = q.front();
            q.pop();
            if (force[i].size() == 1) {
                char f = force[i][0];
                ans[i] = f;
                int j = (f == 'L') ? (i - 1) : (i + 1);
                if (j >= 0 && j < n) {
                    int t = time[i];
                    if (time[j] == -1) {
                        q.emplace(j);
                        time[j] = t + 1;
                        force[j].push_back(f);
                    } else if (time[j] == t + 1)
                        force[j].push_back(f);
                }
            }
        }
        return ans;
    }
};