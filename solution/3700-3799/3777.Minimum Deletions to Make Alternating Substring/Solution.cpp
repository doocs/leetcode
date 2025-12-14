class BinaryIndexedTree {
public:
    int n;
    vector<int> c;

    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1, 0) {}

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
    vector<int> minDeletions(string s, vector<vector<int>>& queries) {
        int n = s.size();
        vector<int> nums(n, 0);
        BinaryIndexedTree bit(n);

        for (int i = 1; i < n; i++) {
            nums[i] = (s[i] == s[i - 1]);
            if (nums[i]) {
                bit.update(i + 1, 1);
            }
        }

        vector<int> ans;

        for (auto& q : queries) {
            if (q[0] == 1) {
                int j = q[1];

                int delta = (nums[j] ^ 1) - nums[j];
                nums[j] ^= 1;
                bit.update(j + 1, delta);

                if (j + 1 < n) {
                    delta = (nums[j + 1] ^ 1) - nums[j + 1];
                    nums[j + 1] ^= 1;
                    bit.update(j + 2, delta);
                }
            } else {
                int l = q[1];
                int r = q[2];
                ans.push_back(bit.query(r + 1) - bit.query(l + 1));
            }
        }
        return ans;
    }
};
