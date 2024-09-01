class Solution {
public:
    int generateKey(int num1, int num2, int num3) {
        int ans = 0, k = 1;
        for (int i = 0; i < 4; ++i) {
            int x = min({num1 / k % 10, num2 / k % 10, num3 / k % 10});
            ans += x * k;
            k *= 10;
        }
        return ans;
    }
};
