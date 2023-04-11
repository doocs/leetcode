class Solution {
public:
    int longestDecomposition(string text) {
        int ans = 0;
        auto check = [&](int i, int j, int k) -> bool {
            while (k--) {
                if (text[i++] != text[j++]) {
                    return false;
                }
            }
            return true;
        };
        for (int i = 0, j = text.size() - 1; i <= j;) {
            bool ok = false;
            for (int k = 1; i + k - 1 < j - k + 1; ++k) {
                if (check(i, j - k + 1, k)) {
                    ans += 2;
                    i += k;
                    j -= k;
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                ans += 1;
                break;
            }
        }
        return ans;
    }
};