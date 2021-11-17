class Solution {
public:
    int maxProduct(vector<string>& words) {
        int n = words.size();
        vector<int> masks(n);
        for (int i = 0; i < n; ++i)
            for (char c : words[i])
                masks[i] |= (1 << (c - 'a'));
        int ans = 0;
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                if ((masks[i] & masks[j]) == 0)
                    ans = max(ans, (int) (words[i].size() * words[j].size()));
        return ans;
    }
};