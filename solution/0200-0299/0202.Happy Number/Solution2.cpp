class Solution {
public:
    bool isHappy(int n) {
        auto next = [](int x) {
            int y = 0;
            for (; x; x /= 10) {
                y += pow(x % 10, 2);
            }
            return y;
        };
        int slow = n, fast = next(n);
        while (slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return slow == 1;
    }
};