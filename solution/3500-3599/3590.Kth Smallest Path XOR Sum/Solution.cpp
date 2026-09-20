class BinarySumTrie {
public:
    int count = 0;
    BinarySumTrie* children[2]{};

    void add(int num, int delta, int bit) {
        count += delta;
        if (bit < 0) {
            return;
        }
        int b = (num >> bit) & 1;
        if (!children[b]) {
            children[b] = new BinarySumTrie();
        }
        children[b]->add(num, delta, bit - 1);
    }

    void collect(int prefix, int bit, vector<int>& output) {
        if (count == 0) {
            return;
        }
        if (bit < 0) {
            output.push_back(prefix);
            return;
        }
        if (children[0]) {
            children[0]->collect(prefix, bit - 1, output);
        }
        if (children[1]) {
            children[1]->collect(prefix | (1 << bit), bit - 1, output);
        }
    }

    bool exists(int num, int bit) {
        if (count == 0) {
            return false;
        }
        if (bit < 0) {
            return true;
        }
        int b = (num >> bit) & 1;
        return children[b] && children[b]->exists(num, bit - 1);
    }

    int findKth(int k, int bit) {
        if (k > count) {
            return -1;
        }
        if (bit < 0) {
            return 0;
        }
        int leftCount = children[0] ? children[0]->count : 0;
        if (k <= leftCount) {
            return children[0]->findKth(k, bit - 1);
        }
        if (children[1]) {
            return (1 << bit) + children[1]->findKth(k - leftCount, bit - 1);
        }
        return -1;
    }
};

class Solution {
public:
    vector<int> kthSmallest(vector<int>& par, vector<int>& vals, vector<vector<int>>& queries) {
        int n = par.size();
        tree.assign(n, {});
        for (int i = 1; i < n; ++i) {
            tree[par[i]].push_back(i);
        }
        pathXor = vals;
        computeXor(0, 0);
        nodeQueries.assign(n, {});
        for (int i = 0; i < (int) queries.size(); ++i) {
            nodeQueries[queries[i][0]].push_back({queries[i][1], i});
        }
        pool.assign(n, nullptr);
        result.assign(queries.size(), 0);
        dfs(0);
        return result;
    }

private:
    static constexpr int BITS = 17;
    vector<vector<int>> tree;
    vector<int> pathXor;
    vector<vector<pair<int, int>>> nodeQueries;
    vector<BinarySumTrie*> pool;
    vector<int> result;

    void computeXor(int node, int acc) {
        pathXor[node] ^= acc;
        for (int child : tree[node]) {
            computeXor(child, pathXor[node]);
        }
    }

    void dfs(int node) {
        pool[node] = new BinarySumTrie();
        pool[node]->add(pathXor[node], 1, BITS);
        for (int child : tree[node]) {
            dfs(child);
            if (pool[node]->count < pool[child]->count) {
                swap(pool[node], pool[child]);
            }
            vector<int> vals;
            pool[child]->collect(0, BITS, vals);
            for (int val : vals) {
                if (!pool[node]->exists(val, BITS)) {
                    pool[node]->add(val, 1, BITS);
                }
            }
        }
        for (auto [k, idx] : nodeQueries[node]) {
            result[idx] = pool[node]->count < k ? -1 : pool[node]->findKth(k, BITS);
        }
    }
};
