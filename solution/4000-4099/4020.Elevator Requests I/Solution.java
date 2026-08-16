class Solution {
    public int elevatorRequests(int n, int[] requests) {
        int ans = requests[0];
        for (int i = 1; i < requests.length; ++i) {
            ans += Math.abs(requests[i - 1] - requests[i]);
        }
        return ans;
    }
}