class Solution {
public:
    int isWinner(vector<int>& player1, vector<int>& player2) {
        int a = f(player1), b = f(player2);
        return a > b ? 1 : b > a ? 2
                                 : 0;
    }

    int f(vector<int>& arr) {
        int s = 0;
        for (int i = 0; i < arr.size(); ++i) {
            int k = (i > 0 && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) ? 2 : 1;
            s += k * arr[i];
        }
        return s;
    }
};