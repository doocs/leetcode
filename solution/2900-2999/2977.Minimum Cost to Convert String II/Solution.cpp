class Node {
public:
    Node* children[26];
    int v = -1;
    Node() {
        fill(children, children + 26, nullptr);
    }
};

class Solution {
private:
    const long long inf = 1LL << 60;
    Node* root = new Node();
    int idx;

    vector<vector<long long>> g;
    string s;
    string t;
    vector<long long> f;

public:
    long long minimumCost(string source, string target, vector<string>& original, vector<string>& changed, vector<int>& cost) {
        int m = cost.size();
        g = vector<vector<long long>>(m << 1, vector<long long>(m << 1, inf));
        s = source;
        t = target;

        for (int i = 0; i < g.size(); ++i) {
            g[i][i] = 0;
        }

        for (int i = 0; i < m; ++i) {
            int x = insert(original[i]);
            int y = insert(changed[i]);
            g[x][y] = min(g[x][y], static_cast<long long>(cost[i]));
        }

        for (int k = 0; k < idx; ++k) {
            for (int i = 0; i < idx; ++i) {
                if (g[i][k] >= inf) {
                    continue;
                }
                for (int j = 0; j < idx; ++j) {
                    g[i][j] = min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }

        f = vector<long long>(s.length(), -1);
        long long ans = dfs(0);
        return ans >= inf ? -1 : ans;
    }

private:
    int insert(const string& w) {
        Node* node = root;
        for (char c : w) {
            int i = c - 'a';
            if (node->children[i] == nullptr) {
                node->children[i] = new Node();
            }
            node = node->children[i];
        }
        if (node->v < 0) {
            node->v = idx++;
        }
        return node->v;
    }

    long long dfs(int i) {
        if (i >= s.length()) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        long long res = (s[i] == t[i]) ? dfs(i + 1) : inf;
        Node* p = root;
        Node* q = root;
        for (int j = i; j < s.length(); ++j) {
            p = p->children[s[j] - 'a'];
            q = q->children[t[j] - 'a'];
            if (p == nullptr || q == nullptr) {
                break;
            }
            if (p->v < 0 || q->v < 0) {
                continue;
            }
            long long temp = g[p->v][q->v];
            if (temp < inf) {
                res = min(res, temp + dfs(j + 1));
            }
        }
        return f[i] = res;
    }
};