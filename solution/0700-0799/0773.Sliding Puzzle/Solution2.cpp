class Solution {
public:
    int m = 2;
    int n = 3;

    int slidingPuzzle(vector<vector<int>>& board) {
        string start, seq;
        string end = "123450";
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                start += char(board[i][j] + '0');
                if (board[i][j] != 0) seq += char(board[i][j] + '0');
            }
        }
        if (!check(seq)) return -1;
        typedef pair<int, string> PIS;
        priority_queue<PIS, vector<PIS>, greater<PIS>> q;
        unordered_map<string, int> dist;
        dist[start] = 0;
        q.push({f(start), start});
        vector<int> dirs = {-1, 0, 1, 0, -1};
        while (!q.empty()) {
            PIS t = q.top();
            q.pop();
            string state = t.second;
            int step = dist[state];
            if (state == end) return step;
            int p1 = state.find('0');
            int i = p1 / n, j = p1 % n;
            for (int k = 0; k < 4; ++k) {
                int x = i + dirs[k], y = j + dirs[k + 1];
                if (x < 0 || x >= m || y < 0 || y >= n) continue;
                int p2 = x * n + y;
                swap(state[p1], state[p2]);
                if (!dist.count(state) || dist[state] > step + 1) {
                    dist[state] = step + 1;
                    q.push({step + 1 + f(state), state});
                }
                swap(state[p1], state[p2]);
            }
        }
        return -1;
    }

    bool check(string s) {
        int n = s.size();
        int cnt = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i; j < n; ++j)
                if (s[i] > s[j])
                    ++cnt;
        return cnt % 2 == 0;
    }

    int f(string s) {
        int ans = 0;
        for (int i = 0; i < m * n; ++i) {
            if (s[i] == '0') continue;
            int num = s[i] - '1';
            ans += abs(num / n - i / n) + abs(num % n - i % n);
        }
        return ans;
    }
};