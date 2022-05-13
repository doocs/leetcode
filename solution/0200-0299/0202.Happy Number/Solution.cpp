class Solution {
public:
    bool isHappy(int n) {
        auto getNext = [](int n) {
            int res = 0;
            while (n) {
                res += pow(n % 10, 2);
                n /= 10;
            }
            return res;
        };
        int slow = n;
        int fast = getNext(n);
        while (slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return slow == 1;
    }
};