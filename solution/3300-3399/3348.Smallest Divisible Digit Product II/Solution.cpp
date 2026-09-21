class Solution {
public:
    string smallestNumber(string num, long long t) {
        int required[4]{};
        if (!factorize(t, required)) {
            return "-1";
        }
        int need[10]{};
        toDigits(required, need);
        if (sum(need, 10) > (int) num.size()) {
            return construct(need);
        }

        int prefix[4]{};
        for (char ch : num) {
            add(prefix, DIGIT_PRIMES[ch - '0']);
        }
        int firstZero = num.find('0');
        if (firstZero == (int) string::npos) {
            firstZero = num.size();
            if (isSubset(required, prefix)) {
                return num;
            }
        }

        int n = num.size();
        for (int i = n - 1; i >= 0; --i) {
            subtractInPlace(prefix, DIGIT_PRIMES[num[i] - '0']);
            int space = n - 1 - i;
            if (i > firstZero) {
                continue;
            }
            for (int bigger = num[i] - '0' + 1; bigger < 10; ++bigger) {
                int tmp[4]{}, suffix[10]{};
                subtract(required, prefix, tmp);
                subtractInPlace(tmp, DIGIT_PRIMES[bigger]);
                toDigits(tmp, suffix);
                if (sum(suffix, 10) <= space) {
                    return num.substr(0, i) + char('0' + bigger) + string(space - sum(suffix, 10), '1') + construct(suffix);
                }
            }
        }
        int ext[10]{};
        toDigits(required, ext);
        return string(n + 1 - sum(ext, 10), '1') + construct(ext);
    }

private:
    static constexpr int DIGIT_PRIMES[10][4] = {
        {0, 0, 0, 0},
        {0, 0, 0, 0},
        {1, 0, 0, 0},
        {0, 1, 0, 0},
        {2, 0, 0, 0},
        {0, 0, 1, 0},
        {1, 1, 0, 0},
        {0, 0, 0, 1},
        {3, 0, 0, 0},
        {0, 2, 0, 0},
    };

    bool factorize(long long t, int counts[4]) {
        int primes[4] = {2, 3, 5, 7};
        for (int i = 0; i < 4; ++i) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                ++counts[i];
            }
        }
        return t == 1;
    }

    void toDigits(const int primes[4], int digits[10]) {
        int count8 = primes[0] / 3;
        int remaining2 = primes[0] % 3;
        int count9 = primes[1] / 2;
        int count3 = primes[1] % 2;
        int count4 = remaining2 / 2;
        int count2 = remaining2 % 2;
        int count6 = 0;
        if (count2 == 1 && count3 == 1) {
            count2 = 0;
            count3 = 0;
            count6 = 1;
        }
        if (count3 == 1 && count4 == 1) {
            count2 = 1;
            count6 = 1;
            count3 = 0;
            count4 = 0;
        }
        int vals[10] = {0, 0, count2, count3, count4, primes[2], count6, primes[3], count8, count9};
        for (int i = 0; i < 10; ++i) {
            digits[i] = vals[i];
        }
    }

    string construct(const int digits[10]) {
        string res;
        for (int d = 2; d < 10; ++d) {
            res.append(digits[d], char('0' + d));
        }
        return res;
    }

    bool isSubset(const int a[4], const int b[4]) {
        for (int i = 0; i < 4; ++i) {
            if (b[i] < a[i]) {
                return false;
            }
        }
        return true;
    }

    void subtract(const int a[4], const int b[4], int res[4]) {
        for (int i = 0; i < 4; ++i) {
            res[i] = max(0, a[i] - b[i]);
        }
    }

    void subtractInPlace(int a[4], const int b[4]) {
        for (int i = 0; i < 4; ++i) {
            a[i] = max(0, a[i] - b[i]);
        }
    }

    void add(int a[4], const int b[4]) {
        for (int i = 0; i < 4; ++i) {
            a[i] += b[i];
        }
    }

    int sum(const int* a, int n) {
        int s = 0;
        for (int i = 0; i < n; ++i) {
            s += a[i];
        }
        return s;
    }
};
