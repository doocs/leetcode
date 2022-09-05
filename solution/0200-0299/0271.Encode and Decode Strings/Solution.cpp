class Codec {
public:
    // Encodes a list of strings to a single string.
    string encode(vector<string>& strs) {
        string ans;
        for (string s : strs) {
            int size = s.size();
            ans += string((const char*) &size, sizeof(size));
            ans += s;
        }
        return ans;
    }

    // Decodes a single string to a list of strings.
    vector<string> decode(string s) {
        vector<string> ans;
        int i = 0, n = s.size();
        int size = 0;
        while (i < n) {
            memcpy(&size, s.data() + i, sizeof(size));
            i += sizeof(size);
            ans.push_back(s.substr(i, size));
            i += size;
        }
        return ans;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec codec;
// codec.decode(codec.encode(strs));