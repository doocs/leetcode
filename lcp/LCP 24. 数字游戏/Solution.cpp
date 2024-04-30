class MedianFinder {
public:
    MedianFinder() {
    }

    void addNum(int num) {
        q1.push(num);
        s1 += num;
        num = q1.top();
        q2.push(num);
        q1.pop();
        s2 += num;
        s1 -= num;
        if (q2.size() - q1.size() > 1) {
            num = q2.top();
            q1.push(num);
            q2.pop();
            s1 += num;
            s2 -= num;
        }
    }

    int findMedian() {
        if (q2.size() > q1.size()) {
            return q2.top();
        }
        return (q1.top() + q2.top()) / 2;
    }

    int cal() {
        long long x = findMedian();
        return (s1 - x * q1.size() + x * q2.size() - s2) % mod;
    }

private:
    priority_queue<int, vector<int>, greater<int>> q1;
    priority_queue<int> q2;
    long long s1 = 0;
    long long s2 = 0;
    const int mod = 1e9 + 7;
};

class Solution {
public:
    vector<int> numsGame(vector<int>& nums) {
        int n = nums.size();
        vector<int> ans(n);
        MedianFinder finder;
        for (int i = 0; i < n; ++i) {
            finder.addNum(nums[i] - i);
            ans[i] = finder.cal();
        }
        return ans;
    }
};