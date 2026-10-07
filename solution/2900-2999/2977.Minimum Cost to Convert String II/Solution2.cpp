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
    int idx = 0;

    vector<vector<long long>> g;

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

public:
    long long minimumCost(string source, string target, vector<string>& original, vector<string>& changed, vector<int>& cost) {
        int m = cost.size();
        g = vector<vector<long long>>(m << 1, vector<long long>(m << 1, inf));
        for (int i = 0; i < (int) g.size(); ++i) {
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
        int n = source.size();
        vector<long long> f(n + 1, inf);
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            long long res = source[i] == target[i] ? f[i + 1] : inf;
            Node* p = root;
            Node* q = root;
            for (int j = i; j < n; ++j) {
                p = p->children[source[j] - 'a'];
                q = q->children[target[j] - 'a'];
                if (p == nullptr || q == nullptr) {
                    break;
                }
                if (p->v < 0 || q->v < 0) {
                    continue;
                }
                long long w = g[p->v][q->v];
                if (w < inf) {
                    res = min(res, w + f[j + 1]);
                }
            }
            f[i] = res;
        }
        return f[0] >= inf ? -1 : f[0];
    }
};
