class Solution {
public:
    string decodeCiphertext(string encodedText, int rows) {
        string ans;
        int cols = encodedText.size() / rows;
        for (int j = 0; j < cols; ++j)
            for (int x = 0, y = j; x < rows && y < cols; ++x, ++y)
                ans += encodedText[x * cols + y];
        while (ans.back() == ' ') ans.pop_back();
        return ans;
    }
};