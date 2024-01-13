class Solution {
public:
    string entityParser(string text) {
        unordered_map<string, string> d = {
            {"&quot;", "\""},
            {"&apos;", "'"},
            {"&amp;", "&"},
            {"&gt;", ">"},
            {"&lt;", "<"},
            {"&frasl;", "/"},
        };
        string ans = "";
        int i = 0, n = text.size();
        while (i < n) {
            bool found = false;
            for (int l = 1; l < 8; ++l) {
                int j = i + l;
                if (j <= n) {
                    string t = text.substr(i, l);
                    if (d.count(t)) {
                        ans += d[t];
                        i = j;
                        found = true;
                        break;
                    }
                }
            }
            if (!found) ans += text[i++];
        }
        return ans;
    }
};