class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        vector<string> ans;
        for (int i = 0, n = words.size(); i < n;) {
            vector<string> t = {words[i]};
            int cnt = words[i].size();
            ++i;
            while (i < n && cnt + 1 + words[i].size() <= maxWidth) {
                cnt += 1 + words[i].size();
                t.emplace_back(words[i++]);
            }
            if (i == n || t.size() == 1) {
                string left = t[0];
                for (int j = 1; j < t.size(); ++j) {
                    left += " " + t[j];
                }
                string right = string(maxWidth - left.size(), ' ');
                ans.emplace_back(left + right);
                continue;
            }
            int spaceWidth = maxWidth - (cnt - t.size() + 1);
            int w = spaceWidth / (t.size() - 1);
            int m = spaceWidth % (t.size() - 1);
            string row;
            for (int j = 0; j < t.size() - 1; ++j) {
                row += t[j] + string(w + (j < m ? 1 : 0), ' ');
            }
            row += t.back();
            ans.emplace_back(row);
        }
        return ans;
    }
};