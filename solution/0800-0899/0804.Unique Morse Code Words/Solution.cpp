class Solution {
public:
    int uniqueMorseRepresentations(vector<string>& words) {
        vector<string> codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        unordered_set<string> s;
        for (auto& word : words) {
            string t;
            for (char& c : word) t += codes[c - 'a'];
            s.insert(t);
        }
        return s.size();
    }
};