class BinaryIndexedTree {
public:
    BinaryIndexedTree(int n) {
        this->n = n;
        this->c = vector<int>(n + 1, 0);
    }

    void update(int x, int val) {
        while (x <= n) {
            c[x] += val;
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

private:
    int n;
    vector<int> c;
};

class Solution {
public:
    bool find132pattern(vector<int>& nums) {
        vector<int> s = nums;
        sort(s.begin(), s.end());
        s.erase(unique(s.begin(), s.end()), s.end());
        BinaryIndexedTree tree(s.size());
        int n = nums.size();
        int left[n + 1];
        memset(left, 63, sizeof(left));
        for (int i = 0; i < n; ++i) {
            left[i + 1] = min(left[i], nums[i]);
        }
        for (int i = nums.size() - 1; ~i; --i) {
            int x = lower_bound(s.begin(), s.end(), nums[i]) - s.begin() + 1;
            int y = lower_bound(s.begin(), s.end(), left[i]) - s.begin() + 1;
            if (x > y && tree.query(x - 1) > tree.query(y)) {
                return true;
            }
            tree.update(x, 1);
        }
        return false;
    }
};