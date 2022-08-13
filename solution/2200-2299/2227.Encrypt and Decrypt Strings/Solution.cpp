class Encrypter {
public:
    unordered_map<string, int> cnt;
    unordered_map<char, string> mp;

    Encrypter(vector<char>& keys, vector<string>& values, vector<string>& dictionary) {
        for (int i = 0; i < keys.size(); ++i) mp[keys[i]] = values[i];
        for (auto v : dictionary) cnt[encrypt(v)]++;
    }

    string encrypt(string word1) {
        string res = "";
        for (char c : word1) {
            if (!mp.count(c)) return "";
            res += mp[c];
        }
        return res;
    }

    int decrypt(string word2) {
        return cnt[word2];
    }
};

/**
 * Your Encrypter object will be instantiated and called as such:
 * Encrypter* obj = new Encrypter(keys, values, dictionary);
 * string param_1 = obj->encrypt(word1);
 * int param_2 = obj->decrypt(word2);
 */