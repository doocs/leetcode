class Solution {
public:
    vector<vector<string>> wordSquares(vector<string>& words) {
        ranges::sort(words);
        int n = words.size();
        vector<vector<string>> ans;

        for (int i = 0; i < n; i++) {
            string top = words[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    string left = words[j];
                    for (int k = 0; k < n; k++) {
                        if (k != j && k != i) {
                            string right = words[k];
                            for (int h = 0; h < n; h++) {
                                if (h != k && h != j && h != i) {
                                    string bottom = words[h];
                                    if (top[0] == left[0] && top[3] == right[0] && bottom[0] == left[3] && bottom[3] == right[3]) {
                                        ans.push_back({top, left, right, bottom});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
};
