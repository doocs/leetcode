public class Solution {
    public int KnightDialer(int n) {
        if (n == 1) return 10;
        int A = 4;
        int B = 2;
        int C = 2;
        int D = 1;
        int MOD = (int)1e9 + 7;
        for (int i = 0; i < n - 1; i++) {
            int tempA = A;
            int tempB = B;
            int tempC = C;
            int tempD = D;
            A = ((2 * tempB) % MOD + (2 * tempC) % MOD) % MOD;
            B = tempA;
            C = (tempA + (2 * tempD) % MOD) % MOD;
            D = tempC;
        }

        int ans = (A + B) % MOD;
        ans = (ans + C) % MOD;
        return (ans + D) % MOD;
    }
}
