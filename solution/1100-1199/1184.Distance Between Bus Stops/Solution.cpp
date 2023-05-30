class Solution {
public:
    int distanceBetweenBusStops(vector<int>& distance, int start, int destination) {
        int s = accumulate(distance.begin(), distance.end(), 0);
        int a = 0, n = distance.size();
        while (start != destination) {
            a += distance[start];
            start = (start + 1) % n;
        }
        return min(a, s - a);
    }
};