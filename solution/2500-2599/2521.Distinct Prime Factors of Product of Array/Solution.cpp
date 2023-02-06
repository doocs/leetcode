class Solution {
public:
    int distinctPrimeFactors(vector<int>& nums) {
        unordered_set<int> s;
        for (int& n : nums) {
            for (int i = 2; i <= n / i; ++i) {
                if (n % i == 0) {
                    s.insert(i);
                    while (n % i == 0) {
                        n /= i;
                    }
                }
            }
            if (n > 1) {
                s.insert(n);
            }
        }
        return s.size();
    }
};