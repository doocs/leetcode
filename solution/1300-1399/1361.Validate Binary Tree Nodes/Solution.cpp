class Solution {
public:
    vector<int> p;

    bool validateBinaryTreeNodes(int n, vector<int>& leftChild, vector<int>& rightChild) {
        p.resize(n);
        for (int i = 0; i < n; ++i) p[i] = i;
        vector<bool> vis(n, false);
        for (int i = 0, t = n; i < t; ++i) {
            int l = leftChild[i], r = rightChild[i];
            if (l != -1) {
                if (vis[l] || find(i) == find(l)) return false;
                vis[l] = true;
                p[find(i)] = find(l);
                --n;
            }
            if (r != -1) {
                if (vis[r] || find(i) == find(r)) return false;
                vis[r] = true;
                p[find(i)] = find(r);
                --n;
            }
        }
        return n == 1;
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};