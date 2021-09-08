class Solution {
public:
    int game(vector<int>& guess, vector<int>& answer) {
        int times = 0;
        for (int i = 0; i < 3; ++i) times += guess[i] == answer[i];
        return times;
    }
};