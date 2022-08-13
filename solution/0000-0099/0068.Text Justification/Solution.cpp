class Solution {
public:
    vector<string> fullJustify(vector<string>& words, int maxWidth) {
        int n = words.size();
        vector<string> result;
        for (int i = 0; i < n; i++) {
            int begin = i;
            int wordLen = words[i].size();
            while (i + 1 < n && words[i + 1].size() + wordLen + 1 <= maxWidth) {
                wordLen += words[++i].size() + 1;
            }
            int numberofWords = i - begin + 1;
            int space = 1;
            int extraSpace = 0;
            if (numberofWords > 1 && i < n - 1) {
                int remaining = maxWidth - wordLen;
                space = remaining / (numberofWords - 1) + 1;
                extraSpace = remaining % (numberofWords - 1);
            }
            string line = words[begin];
            for (int j = 1; j < numberofWords; j++) {
                line.append(space, ' ');
                if (j <= extraSpace) {
                    line.push_back(' ');
                }
                line += words[begin + j];
            }
            if (line.size() < maxWidth) {
                line.append(maxWidth - line.size(), ' ');
            }
            result.emplace_back(line);
        }
        return result;
    }
};
