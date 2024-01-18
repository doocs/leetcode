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
    bool isIdealPermutation(vector<int>& nums) {
        int n = nums.size();
        BinaryIndexedTree tree(n);
        long cnt = 0;
        for (int i = 0; i < n && ~cnt; ++i) {
            cnt += (i < n - 1 && nums[i] > nums[i + 1]);
            cnt -= (i - tree.query(nums[i]));
            tree.update(nums[i] + 1, 1);
        }
        return cnt == 0;
    }
};