import java.math.BigInteger;

class Solution {
    public String nearestPalindromic(String n) {
        BigInteger x = new BigInteger(n);
        int l = n.length();
        Set<BigInteger> res = new HashSet<>();
        res.add(BigInteger.TEN.pow(l - 1).subtract(BigInteger.ONE));
        res.add(BigInteger.TEN.pow(l).add(BigInteger.ONE));
        BigInteger left = new BigInteger(n.substring(0, (l + 1) / 2));
        for (int d = -1; d <= 1; ++d) {
            BigInteger i = left.add(BigInteger.valueOf(d));
            BigInteger j = l % 2 == 0 ? i : i.divide(BigInteger.TEN);
            while (j.signum() > 0) {
                i = i.multiply(BigInteger.TEN).add(j.mod(BigInteger.TEN));
                j = j.divide(BigInteger.TEN);
            }
            res.add(i);
        }
        res.remove(x);
        BigInteger ans = null;
        for (BigInteger t : res) {
            BigInteger dist = t.subtract(x).abs();
            if (ans == null || dist.compareTo(ans.subtract(x).abs()) < 0
                || (dist.compareTo(ans.subtract(x).abs()) == 0 && t.compareTo(ans) < 0)) {
                ans = t;
            }
        }
        return ans.toString();
    }
}
