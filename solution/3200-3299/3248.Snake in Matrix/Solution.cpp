class Solution {
public:
    int finalPositionOfSnake(int n, vector<string>& commands) {
        int x = 0, y = 0;
        for (const auto& c : commands) {
            switch (c[0]) {
            case 'U': x--; break;
            case 'D': x++; break;
            case 'L': y--; break;
            case 'R': y++; break;
            }
        }
        return x * n + y;
    }
};
