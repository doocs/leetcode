class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int v : nums1) {
            cnt1.put(v, cnt1.getOrDefault(v, 0) + 1);
        }
        for (int v : nums2) {
            cnt2.put(v, cnt2.getOrDefault(v, 0) + 1);
        }
        long ans = 0;
        for (var e1 : cnt1.entrySet()) {
            long a = e1.getKey(), x = e1.getValue();
            for (var e2 : cnt2.entrySet()) {
                long b = e2.getKey(), y = e2.getValue();
                if ((a * a) % b == 0) {
                    long c = a * a / b;
                    if (b == c) {
                        ans += x * y * (y - 1);
                    } else {
                        ans += x * y * cnt2.getOrDefault((int) c, 0);
                    }
                }
                if ((b * b) % a == 0) {
                    long c = b * b / a;
                    if (a == c) {
                        ans += x * (x - 1) * y;
                    } else {
                        ans += x * y * cnt1.getOrDefault((int) c, 0);
                    }
                }
            }
        }
        return (int) (ans >> 1);
    }
}