class BinaryIndexedTree {
    private int n;
    private int[] c;
    private int[] d;

    public BinaryIndexedTree(int n) {
        this.n = n;
        c = new int[n + 1];
        d = new int[n + 1];
    }

    public void update(int x, int v, int cnt) {
        while (x <= n) {
            if (c[x] < v) {
                c[x] = v;
                d[x] = cnt;
            } else if (c[x] == v) {
                d[x] += cnt;
            }
            x += x & -x;
        }
    }

    public int[] query(int x) {
        int v = 0, cnt = 0;
        while (x > 0) {
            if (c[x] > v) {
                v = c[x];
                cnt = d[x];
            } else if (c[x] == v) {
                cnt += d[x];
            }
            x -= x & -x;
        }
        return new int[] {v, cnt};
    }
}

public class Solution {
    public int findNumberOfLIS(int[] nums) {
        // int[] arr = Arrays.stream(nums).distinct().sorted().toArray();
        int[] arr = nums.clone();
        Arrays.sort(arr);
        int m = arr.length;
        BinaryIndexedTree tree = new BinaryIndexedTree(m);
        for (int x : nums) {
            int i = Arrays.binarySearch(arr, x) + 1;
            int[] t = tree.query(i - 1);
            int v = t[0];
            int cnt = t[1];
            tree.update(i, v + 1, Math.max(cnt, 1));
        }
        return tree.query(m)[1];
    }
}