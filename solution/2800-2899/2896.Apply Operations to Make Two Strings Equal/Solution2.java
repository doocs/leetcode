class Solution {
    public int minOperations(String s1, String s2, int x) {
        int n = s1.length();
        int inf = 50_000;
        int one = inf, two = inf, last = inf;
        int done = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                one = Math.min(one, last);
                last = last + 1;
                two = two + 1;
                continue;
            }
            if (done < n) {
                one = Math.min(two + 1, done + x);
                last = Math.min(two + x, done);
                done = two = inf;
                continue;
            }
            done = Math.min(one + x, last + 1);
            two = one;
            one = last = inf;
            continue;
        }
        return done == inf ? -1 : done;
    }
}