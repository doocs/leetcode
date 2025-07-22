class Solution {
public:
    string reorderSpaces(string text) {
        int spaces = ranges::count(text, ' ');
        auto words = split(text);

        if (words.size() == 1) {
            return words[0] + string(spaces, ' ');
        }

        int cnt = spaces / (words.size() - 1);
        int mod = spaces % (words.size() - 1);

        string result = join(words, string(cnt, ' '));
        result += string(mod, ' ');

        return result;
    }

private:
    vector<string> split(const string& text) {
        vector<string> words;
        istringstream stream(text);
        string word;
        while (stream >> word) {
            words.push_back(word);
        }
        return words;
    }

    string join(const vector<string>& words, const string& separator) {
        ostringstream result;
        for (size_t i = 0; i < words.size(); ++i) {
            result << words[i];
            if (i < words.size() - 1) {
                result << separator;
            }
        }
        return result.str();
    }
};
