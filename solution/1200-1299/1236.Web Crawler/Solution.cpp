/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * class HtmlParser {
 *   public:
 *     vector<string> getUrls(string url);
 * };
 */

class Solution {
public:
    vector<string> ans;
    unordered_set<string> vis;

    vector<string> crawl(string startUrl, HtmlParser htmlParser) {
        dfs(startUrl, htmlParser);
        return ans;
    }

    void dfs(string& url, HtmlParser& htmlParser) {
        if (vis.count(url)) return;
        vis.insert(url);
        ans.push_back(url);
        for (string next : htmlParser.getUrls(url))
            if (host(url) == host(next))
                dfs(next, htmlParser);
    }

    string host(string url) {
        int i = 7;
        string res;
        for (; i < url.size(); ++i) {
            if (url[i] == '/') break;
            res += url[i];
        }
        return res;
    }
};