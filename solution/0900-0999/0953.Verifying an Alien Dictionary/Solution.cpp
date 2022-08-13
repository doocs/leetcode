class Solution {
public:
    bool isAlienSorted(vector<string>& words, string order) {
        vector<int> m(26);
        for (int i = 0; i < 26; ++i) m[order[i] - 'a'] = i;
        for (int i = 0; i < 20; ++i) {
            int prev = -1;
            bool valid = true;
            for (auto& x : words) {
                int curr = i >= x.size() ? -1 : m[x[i] - 'a'];
                if (prev > curr) return false;
                if (prev == curr) valid = false;
                prev = curr;
            }
            if (valid) break;
        }
        return true;
    }
};