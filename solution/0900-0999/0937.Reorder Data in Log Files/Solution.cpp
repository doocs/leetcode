class Solution {
public:
    vector<string> reorderLogFiles(vector<string>& logs) {
        stable_sort(logs.begin(), logs.end(), [](const string& log1, const string& log2) {
            int idx1 = log1.find(' ');
            int idx2 = log2.find(' ');
            string id1 = log1.substr(0, idx1);
            string id2 = log2.substr(0, idx2);
            string content1 = log1.substr(idx1 + 1);
            string content2 = log2.substr(idx2 + 1);

            bool isLetter1 = isalpha(content1[0]);
            bool isLetter2 = isalpha(content2[0]);

            if (isLetter1 && isLetter2) {
                if (content1 != content2) {
                    return content1 < content2;
                }
                return id1 < id2;
            }

            return isLetter1 > isLetter2;
        });

        return logs;
    }
};
