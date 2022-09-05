class Solution {
public:
    bool isAdditiveNumber(string num) {
        int n = num.size();
        for (int i = 1; i < min(n - 1, 19); ++i) {
            for (int j = i + 1; j < min(n, i + 19); ++j) {
                if (i > 1 && num[0] == '0') break;
                if (j - i > 1 && num[i] == '0') continue;
                auto a = stoll(num.substr(0, i));
                auto b = stoll(num.substr(i, j - i));
                if (dfs(a, b, num.substr(j, n - j))) return true;
            }
        }
        return false;
    }

    bool dfs(long long a, long long b, string num) {
        if (num == "") return true;
        if (a + b > 0 && num[0] == '0') return false;
        for (int i = 1; i < min((int) num.size() + 1, 19); ++i)
            if (a + b == stoll(num.substr(0, i)))
                if (dfs(b, a + b, num.substr(i, num.size() - i)))
                    return true;
        return false;
    }
};