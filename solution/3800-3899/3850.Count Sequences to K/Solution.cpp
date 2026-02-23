class Solution {
public:
    int n;
    long long k;
    vector<int>* nums;
    map<tuple<int, long long, long long>, int> f;

    int countSequences(vector<int>& nums, long long k) {
        this->nums = &nums;
        this->n = nums.size();
        this->k = k;
        f.clear();
        return dfs(0, 1LL, 1LL);
    }

    int dfs(int i, long long p, long long q) {
        if (i == n) {
            return (p == k && q == 1LL) ? 1 : 0;
        }

        auto key = make_tuple(i, p, q);
        if (f.count(key)) return f[key];

        int res = dfs(i + 1, p, q);

        long long x = (*nums)[i];

        long long g1 = gcd(p * x, q);
        res += dfs(i + 1, (p * x) / g1, q / g1);

        long long g2 = gcd(p, q * x);
        res += dfs(i + 1, p / g2, (q * x) / g2);

        f[key] = res;
        return res;
    }
};
