// The rand7() API is already defined for you.
// int rand7();
// @return a random integer in the range 1 to 7

class Solution {
public:
    int rand10() {
        while (1) {
            int i = rand7() - 1;
            int j = rand7();
            int x = i * 7 + j;
            if (x <= 40) {
                return x % 10 + 1;
            }
        }
    }
};