class Solution {
public:
    bool equalFrequency(string word) {
        int cnt[26]{};
        for (char& c : word) {
            ++cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) {
                --cnt[i];
                int x = 0;
                bool ok = true;
                for (int v : cnt) {
                    if (v == 0) {
                        continue;
                    }
                    if (x && v != x) {
                        ok = false;
                        break;
                    }
                    x = v;
                }
                if (ok) {
                    return true;
                }
                ++cnt[i];
            }
        }
        return false;
    }
};