class Solution {
public:
    int maximumScore(int a, int b, int c) {
        priority_queue<int> pq;
        pq.push(a);
        pq.push(b);
        pq.push(c);
        int ans = 0;
        while (1) {
            a = pq.top(), pq.pop();
            b = pq.top(), pq.pop();
            if (b == 0) {
                break;
            }
            pq.push(a - 1);
            pq.push(b - 1);
            ++ans;
        }
        return ans;
    }
};