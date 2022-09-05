class Bitset {
    private char[] a;
    private char[] b;
    private int cnt;

    public Bitset(int size) {
        a = new char[size];
        b = new char[size];
        Arrays.fill(a, '0');
        Arrays.fill(b, '1');
    }

    public void fix(int idx) {
        if (a[idx] == '0') {
            a[idx] = '1';
            ++cnt;
        }
        b[idx] = '0';
    }

    public void unfix(int idx) {
        if (a[idx] == '1') {
            a[idx] = '0';
            --cnt;
        }
        b[idx] = '1';
    }

    public void flip() {
        char[] t = a;
        a = b;
        b = t;
        cnt = a.length - cnt;
    }

    public boolean all() {
        return cnt == a.length;
    }

    public boolean one() {
        return cnt > 0;
    }

    public int count() {
        return cnt;
    }

    public String toString() {
        return String.valueOf(a);
    }
}

/**
 * Your Bitset object will be instantiated and called as such:
 * Bitset obj = new Bitset(size);
 * obj.fix(idx);
 * obj.unfix(idx);
 * obj.flip();
 * boolean param_4 = obj.all();
 * boolean param_5 = obj.one();
 * int param_6 = obj.count();
 * String param_7 = obj.toString();
 */