class Solution {
public:
    long long sumOfLargestPrimes(string s) {
        unordered_set<long long> st;
        int n = s.size();

        for (int i = 0; i < n; ++i) {
            long long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + (s[j] - '0');
                if (is_prime(x)) {
                    st.insert(x);
                }
            }
        }

        vector<long long> sorted(st.begin(), st.end());
        sort(sorted.begin(), sorted.end());

        long long ans = 0;
        int cnt = 0;
        for (int i = (int) sorted.size() - 1; i >= 0 && cnt < 3; --i, ++cnt) {
            ans += sorted[i];
        }
        return ans;
    }

private:
    bool is_prime(long long x) {
        if (x < 2) return false;
        for (long long i = 2; i * i <= x; ++i) {
            if (x % i == 0) return false;
        }
        return true;
    }
};