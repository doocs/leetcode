class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, this::cmp);
        return logs;
    }

    private int cmp(String a, String b) {
        String[] t1 = a.split(" ", 2);
        String[] t2 = b.split(" ", 2);
        boolean d1 = Character.isDigit(t1[1].charAt(0));
        boolean d2 = Character.isDigit(t2[1].charAt(0));
        if (!d1 && !d2) {
            int v = t1[1].compareTo(t2[1]);
            return v == 0 ? t1[0].compareTo(t2[0]) : v;
        }
        if (d1 && d2) {
            return 0;
        }
        return d1 ? 1 : -1;
    }
}