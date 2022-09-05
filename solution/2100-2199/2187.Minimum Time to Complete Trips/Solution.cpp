class Solution {
public:
    long long minimumTime(vector<int>& time, int totalTrips) {
        int mi = *min_element(time.begin(), time.end());
        long long left = 1, right = (long long) mi * totalTrips;
        while (left < right) {
            long long cnt = 0;
            long long mid = (left + right) >> 1;
            for (int v : time) cnt += mid / v;
            if (cnt >= totalTrips)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
};