class Solution {
public:
    string shortestCompletingWord(string licensePlate, vector<string>& words) {
        vector<int> counter = count(licensePlate);
        int n = 16;
        string ans;
        for (auto& word : words) {
            if (n <= word.size()) continue;
            vector<int> t = count(word);
            if (check(counter, t)) {
                n = word.size();
                ans = word;
            }
        }
        return ans;
    }

    vector<int> count(string& word) {
        vector<int> counter(26);
        for (char& c : word)
            if (isalpha(c))
                ++counter[tolower(c) - 'a'];
        return counter;
    }

    bool check(vector<int>& counter1, vector<int>& counter2) {
        for (int i = 0; i < 26; ++i)
            if (counter1[i] > counter2[i])
                return false;
        return true;
    }
};