class BinaryIndexedTree {
    int n;
    vector<int> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class Solution {
public:
    int minOperations(vector<int>& nums, vector<int>& locked, int k) {
        int n = nums.size();
        BinaryIndexedTree bit(n);
        int inv = 0;
        for (int i = n - 1; i >= 0; --i) {
            inv += bit.query(nums[i] - 1);
            bit.update(nums[i], 1);
        }

        int cnt = 0, mx = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            mx = max(mx, nums[i]);
            if (mx == i + 1) {
                if (i > j) {
                    for (int p = j; p <= i; ++p) {
                        cnt += locked[p];
                    }
                }
                j = i + 1;
            }
        }

        return cnt > k ? -1 : cnt + inv;
    }
};
