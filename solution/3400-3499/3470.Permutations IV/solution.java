import java.util.*;

class DPHelper {
    static final long ok = 10000000000000000L;
    long[][][] dp = new long[101][101][2];
    boolean[][][] vis = new boolean[101][101][2];

    long compute(int o, int e, int p) {
        if (o == 0 && e == 0) return 1;
        if (vis[o][e][p]) return dp[o][e][p];

        long r = 0;
        if (p == 1) {
            if (o == 0) r = 0;
            else r = o * compute(o - 1, e, 0);
        } else {
            if (e == 0) r = 0;
            else r = e * compute(o, e - 1, 1);
        }

        if (r > ok) r = ok;
        vis[o][e][p] = true;
        dp[o][e][p] = r;
        return r;
    }
}

class SortHelper {
    void sortList(ArrayList<Integer> list) {
        Collections.sort(list);
    }
}

class PermutationHelper {
    List<Integer> buildPermutation(int p, ArrayList<Integer> O, ArrayList<Integer> E, long k, DPHelper d) {
        List<Integer> ans = new ArrayList<>();
        if (O.size() + E.size() == 0) return ans;
        int i = 0;

        if (p == 1) {
            while (i < O.size()) {
                long cnt = d.compute(O.size() - 1, E.size(), 0);
                if (k > cnt) {
                    k -= cnt;
                    i++;
                } else {
                    int x = O.get(i);
                    O.remove(i);
                    ans.add(x);
                    ans.addAll(buildPermutation(0, O, E, k, d));
                    return ans;
                }
            }
        } else {
            while (i < E.size()) {
                long cnt = d.compute(O.size(), E.size() - 1, 1);
                if (k > cnt) {
                    k -= cnt;
                    i++;
                } else {
                    int x = E.get(i);
                    E.remove(i);
                    ans.add(x);
                    ans.addAll(buildPermutation(1, O, E, k, d));
                    return ans;
                }
            }
        }
        return ans;
    }

    List<Integer> alternateFormation(ArrayList<Integer> O, ArrayList<Integer> E, long k, DPHelper d, int n, SortHelper s) {
        List<Integer> ans = new ArrayList<>();
        int tot = O.size() + E.size();
        if (tot % 2 == 1) {
            int i = 0;
            while (i < O.size()) {
                long cnt = d.compute(O.size() - 1, E.size(), 0);
                if (k > cnt) {
                    k -= cnt;
                } else {
                    int x = O.get(i);
                    O.remove(i);
                    ans.add(x);
                    ans.addAll(buildPermutation(0, O, E, k, d));
                    return ans;
                }
                i++;
            }
        } else {
            ArrayList<Integer> U = new ArrayList<>();
            U.addAll(O);
            U.addAll(E);
            s.sortList(U);
            int i = 0;
            while (i < U.size()) {
                int x = U.get(i);
                if (O.contains(x)) {
                    long cnt = d.compute(O.size() - 1, E.size(), 0);
                    if (k > cnt) {
                        k -= cnt;
                    } else {
                        int idx = O.indexOf(x);
                        O.remove(idx);
                        ans.add(x);
                        ans.addAll(buildPermutation(0, O, E, k, d));
                        return ans;
                    }
                } else {
                    long cnt = d.compute(O.size(), E.size() - 1, 1);
                    if (k > cnt) {
                        k -= cnt;
                    } else {
                        int idx = E.indexOf(x);
                        E.remove(idx);
                        ans.add(x);
                        ans.addAll(buildPermutation(1, O, E, k, d));
                        return ans;
                    }
                }
                i++;
            }
        }
        return ans;
    }
}

class Solution {
    public int[] permute(int n, long k) {
        int o = (n + 1) / 2, e = n / 2;
        ArrayList<Integer> O = new ArrayList<>(), E = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (i % 2 == 1) O.add(i);
            else E.add(i);
        }

        SortHelper s = new SortHelper();
        s.sortList(O);
        s.sortList(E);

        DPHelper d = new DPHelper();
        PermutationHelper ph = new PermutationHelper();

        long tot = 0;
        if (n % 2 == 1) tot = d.compute(O.size() - 1, E.size(), 0) * O.size();
        else tot = d.compute(O.size() - 1, E.size(), 0) * O.size() + d.compute(O.size(), E.size() - 1, 1) * E.size();

        if (k > tot) return new int[0];

        List<Integer> res = ph.alternateFormation(O, E, k, d, n, s);
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) ans[i] = res.get(i);

        return ans;
    }
}
