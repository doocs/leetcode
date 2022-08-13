class Solution {
public:
    string entityParser(string text) {
        unordered_map<string, string> d;
        d["&quot;"] = "\"";
        d["&apos;"] = "'";
        d["&amp;"] = "&";
        d["&gt;"] = ">";
        d["&lt;"] = "<";
        d["&frasl;"] = "/";
        string ans = "";
        int i = 0, n = text.size();
        while (i < n) {
            bool find = false;
            for (int l = 1; l < 8; ++l) {
                int j = i + l;
                if (j <= n) {
                    string t = text.substr(i, l);
                    if (d.count(t)) {
                        ans += d[t];
                        i = j;
                        find = true;
                        break;
                    }
                }
            }
            if (!find) ans += text[i++];
        }
        return ans;
    }
};