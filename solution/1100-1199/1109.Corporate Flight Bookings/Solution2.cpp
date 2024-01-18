class BinaryIndexedTree {
public:
    BinaryIndexedTree(int _n)
        : n(_n)
        , c(_n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    vector<int> corpFlightBookings(vector<vector<int>>& bookings, int n) {
        BinaryIndexedTree* tree = new BinaryIndexedTree(n);
        for (auto& e : bookings) {
            int first = e[0], last = e[1], seats = e[2];
            tree->update(first, seats);
            tree->update(last + 1, -seats);
        }
        vector<int> ans(n);
        for (int i = 0; i < n; ++i) {
            ans[i] = tree->query(i + 1);
        }
        return ans;
    }
};