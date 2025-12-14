class BinaryIndexedTree {
    int n;
    int[] c;

    BinaryIndexedTree(int n) {
        this.n = n;
        this.c = new int[n + 1];
    }

    void update(int x, int delta) {
        while (x <= n) {
            c[x] += delta;
            x += x & -x;
        }
    }

    int query(int x) {
        int s = 0;
        while (x > 0) {
            s += c[x];
            x -= x & -x;
        }
        return s;
    }
}

class Solution {
    public int[] minDeletions(String s, int[][] queries) {
        int n = s.length();
        int[] nums = new int[n];
        BinaryIndexedTree bit = new BinaryIndexedTree(n);

        for (int i = 1; i < n; i++) {
            nums[i] = (s.charAt(i) == s.charAt(i - 1)) ? 1 : 0;
            if (nums[i] == 1) {
                bit.update(i + 1, 1);
            }
        }

        int cnt = 0;
        for (int[] q : queries) {
            if (q[0] == 2) {
                cnt++;
            }
        }

        int[] ans = new int[cnt];
        int idx = 0;

        for (int[] q : queries) {
            if (q[0] == 1) {
                int j = q[1];

                int delta = (nums[j] ^ 1) - nums[j];
                nums[j] ^= 1;
                bit.update(j + 1, delta);

                if (j + 1 < n) {
                    delta = (nums[j + 1] ^ 1) - nums[j + 1];
                    nums[j + 1] ^= 1;
                    bit.update(j + 2, delta);
                }
            } else {
                int l = q[1];
                int r = q[2];
                ans[idx++] = bit.query(r + 1) - bit.query(l + 1);
            }
        }
        return ans;
    }
}
