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
        const int bits = 17;
        vector<vector<int>> tree(n);
        for (int i = 1; i < n; ++i) {
            tree[par[i]].push_back(i);
        }
        vector<int> pathXor = vals;
        vector<pair<int, int>> stk;
        stk.emplace_back(0, 0);
        while (!stk.empty()) {
            auto [node, acc] = stk.back();
            stk.pop_back();
            pathXor[node] ^= acc;
            for (int i = (int) tree[node].size() - 1; i >= 0; --i) {
                stk.emplace_back(tree[node][i], pathXor[node]);
            }
        }
        vector<vector<pair<int, int>>> nodeQueries(n);
        for (int i = 0; i < (int) queries.size(); ++i) {
            nodeQueries[queries[i][0]].push_back({queries[i][1], i});
        }
        vector<BinarySumTrie*> pool(n, nullptr);
        vector<int> result(queries.size(), 0);
        stk.clear();
        stk.emplace_back(0, 0);
        while (!stk.empty()) {
            auto [node, state] = stk.back();
            stk.pop_back();
            if (state == 0) {
                pool[node] = new BinarySumTrie();
                pool[node]->add(pathXor[node], 1, bits);
                stk.emplace_back(node, 1);
                for (int i = (int) tree[node].size() - 1; i >= 0; --i) {
                    stk.emplace_back(tree[node][i], 0);
                }
                continue;
            }
            for (int child : tree[node]) {
                if (pool[node]->count < pool[child]->count) {
                    swap(pool[node], pool[child]);
                }
                vector<int> got;
                pool[child]->collect(0, bits, got);
                for (int val : got) {
                    if (!pool[node]->exists(val, bits)) {
                        pool[node]->add(val, 1, bits);
                    }
                }
            }
            for (auto [k, idx] : nodeQueries[node]) {
                result[idx] = pool[node]->count < k ? -1 : pool[node]->findKth(k, bits);
            }
        }
        return result;
    }
};
