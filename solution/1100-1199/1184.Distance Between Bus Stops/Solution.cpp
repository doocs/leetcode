class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        int s = accumulate(distance.begin(), distance.end(), 0);
        int t = 0, n = distance.size();
        while (start != destination) {
            t += distance[start];
            start = (start + 1) % n;
        }
        return min(t, s - t);
    }
};
