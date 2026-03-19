class Solution {
public:
    bool isDigitorialPermutation(int n) {
        static int f[10];
        static bool initialized = false;

        if (!initialized) {
            f[0] = 1;
            for (int i = 1; i < 10; i++) {
                f[i] = f[i - 1] * i;
            }
            initialized = true;
        }

        int x = 0;
        int y = n;

        while (y > 0) {
            x += f[y % 10];
            y /= 10;
        }

        string a = to_string(x);
        string b = to_string(n);

        sort(a.begin(), a.end());
        sort(b.begin(), b.end());

        return a == b;
    }
};
