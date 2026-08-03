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
    long long countRatioSubarrays(vector<int>& nums, int a, int b) {
        int n = nums.size();

        vector<long long> s(n + 1);
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + (nums[i] % 2 ? a : -b);
        }

        vector<long long> st = s;
        sort(st.begin(), st.end());
        st.erase(unique(st.begin(), st.end()), st.end());

        BinaryIndexedTree bit(st.size() + 1);

        long long ans = 0;

        for (long long v : s) {
            int x = lower_bound(st.begin(), st.end(), v) - st.begin() + 1;
            ans += bit.query(x);
            bit.update(x, 1);
        }

        return ans;
    }
};