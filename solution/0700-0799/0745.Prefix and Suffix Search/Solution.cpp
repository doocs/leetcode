class WordFilter {
public:
    unordered_map<string, int> d;

    WordFilter(vector<string>& words) {
        for (int k = 0; k < words.size(); ++k) {
            string w = words[k];
            int n = w.size();
            for (int i = 0; i <= n; ++i) {
                string a = w.substr(0, i);
                for (int j = 0; j <= n; ++j) {
                    string b = w.substr(j, n - j);
                    d[a + "." + b] = k;
                }
            }
        }
    }

    int f(string pref, string suff) {
        string key = pref + "." + suff;
        if (d.count(key)) return d[key];
        return -1;
    }
};

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter* obj = new WordFilter(words);
 * int param_1 = obj->f(pref,suff);
 */