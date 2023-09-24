class Solution {
    long long mul(long long x, long long y) {
        return x * y;
    }
    
    pair<int, int> dfs(int x, int f, const vector<vector<int>> &con, const vector<bool> &prime, long long &r) {
        pair<int, int> v = {!prime[x], prime[x]};
        for (int y : con[x]) {
            if (y == f) continue;
            const auto& p = dfs(y, x, con, prime, r);
            r += mul(p.first, v.second) + mul(p.second, v.first);
            if (prime[x]) {
                v.second += p.first;
            } else {
                v.first += p.first;
                v.second += p.second;
            }
            
        }
        return v;
    }
    
public:
    long long countPaths(int n, vector<vector<int>>& edges) {
        vector<bool> prime(n + 1, true);
        prime[1] = false;
        vector<int> all;
        for (int i = 2; i <= n; ++i) {
            if (prime[i]) {
                all.push_back(i);
            }
            for (int x : all) {
                const int temp = i * x;
                if (temp > n) {
                    break;
                }
                prime[temp] = false;
                if (i % x == 0) {
                    break;
                }        
            }
        }
        vector<vector<int>> con(n + 1);
        for (const auto& e : edges) {
            con[e[0]].push_back(e[1]);
            con[e[1]].push_back(e[0]);
        }
        long long r = 0;
        dfs(1, 0, con, prime, r);
        return r;
        
    }
};