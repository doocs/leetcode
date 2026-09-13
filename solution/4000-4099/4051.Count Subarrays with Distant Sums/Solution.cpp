class BinaryIndexedTree {
    int n;
    vector<long long> c;

public:
    BinaryIndexedTree(int n)
        : n(n)
        , c(n + 1) {}

    void update(int x, long long delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    long long query(int x) {
        long long s = 0;
        while (x) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
};

class Solution {
public:
    long long distantSubarrays(vector<int>& nums, int goal, int k) {
        int n = nums.size();
        vector<long long> s(n + 1);

        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        vector<long long> st = s;
        sort(st.begin(), st.end());

        long long ans = 1LL * n * (n + 1) / 2;
        BinaryIndexedTree bit(st.size() + 1);

        for (long long v : s) {
            long long a = v - goal - k + 1LL;
            long long b = v - goal + k - 1LL;

            int l = lower_bound(st.begin(), st.end(), a) - st.begin() + 1;
            int r = lower_bound(st.begin(), st.end(), b + 1) - st.begin();

            if (l <= r) {
                ans -= bit.query(r) - bit.query(l - 1);
            }

            int pos = lower_bound(st.begin(), st.end(), v) - st.begin() + 1;
            bit.update(pos, 1);
        }

        return ans;
    }
};