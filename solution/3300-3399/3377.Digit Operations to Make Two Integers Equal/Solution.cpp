class Solution {
private:
    vector<bool> sieve;
    void runSieve() {
        sieve.resize(100000, true);
        sieve[0] = false, sieve[1] = false;
        for (int i = 2; i < 1e5; ++i) {
            if (sieve[i]) {
                for (int j = 2 * i; j < 1e5; j += i) {
                    sieve[j] = false;
                }
            }
        }
    }
    int solve(int n, int m) {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        unordered_set<int> vis;
        pq.push({n, n});
        while (!pq.empty()) {
            int sum = pq.top().first, cur = pq.top().second;
            pq.pop();
            if (vis.find(cur) != vis.end()) continue;
            vis.insert(cur);
            if (cur == m) return sum;
            string s = to_string(cur);
            for (int i = 0; i < s.size(); ++i) {
                char c = s[i];
                if (s[i] < '9') {
                    s[i]++;
                    int next = stoi(s);
                    if (!sieve[next] && vis.find(next) == vis.end()) {
                        pq.push({sum + next, next});
                    }
                    s[i] = c;
                }
                if (s[i] > '0' && !(i == 0 && s[i] == '1')) {
                    s[i]--;
                    int next = stoi(s);
                    if (!sieve[next] && vis.find(next) == vis.end()) {
                        pq.push({sum + next, next});
                    }
                    s[i] = c;
                }
            }
        }
        return -1;
    }

public:
    int minOperations(int n, int m) {
        runSieve();
        if (sieve[n] || sieve[m]) return -1;
        return solve(n, m);
    }
};
