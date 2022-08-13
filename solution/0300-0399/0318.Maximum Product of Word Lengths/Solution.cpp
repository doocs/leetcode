class Solution {
public:
    int maxProduct(vector<string>& words) {
        int n = words.size();
        vector<int> mask(n);
        for (int i = 0; i < n; ++i)
            for (char ch : words[i])
                mask[i] |= 1 << (ch - 'a');
        int ans = 0;
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                if (!(mask[i] & mask[j]))
                    ans = max(ans, (int)(words[i].size() * words[j].size()));
        return ans;
    }
};