class Solution {
public:
    // Encodes a URL to a shortened URL.
    string encode(string longUrl) {
        string v = to_string(++idx);
        m[v] = longUrl;
        return domain + v;
    }

    // Decodes a shortened URL to its original URL.
    string decode(string shortUrl) {
        int i = shortUrl.rfind('/') + 1;
        return m[shortUrl.substr(i, shortUrl.size() - i)];
    }

private:
    unordered_map<string, string> m;
    int idx = 0;
    string domain = "https://tinyurl.com/";
};

// Your Solution object will be instantiated and called as such:
// Solution solution;
// solution.decode(solution.encode(url));