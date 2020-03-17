class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> begins = new HashSet<>();
        Set<String> deads = new HashSet<>(Arrays.asList(deadends));
        int step = 0;

        begins.add("0000");
        if (begins.contains(target)) {
            return step;
        }

        while (!begins.isEmpty()) {
            if (begins.contains(target)) {
                return step;
            }

            Set<String> temp = new HashSet<>();

            for (String cur : begins) {
                if (deads.contains(cur)) {
                    continue;
                }
                deads.add(cur);
                StringBuffer s = new StringBuffer(cur);
                for (int i = 0; i < 4; i++) {
                    char c = s.charAt(i);
                    String s1 = s.substring(0, i) + (char)(c == '9' ? '0' : c + 1) + s.substring(i + 1, 4);
                    String s2 = s.substring(0, i) + (char)(c == '0' ? '9' : c - 1) + s.substring(i + 1, 4);
                    if (!deads.contains(s1)) {
                        temp.add(s1);
                    }
                    if (!deads.contains(s2)) {
                        temp.add(s2);
                    }
                }
            }
            step ++;
            begins = temp;
        }

        return -1;
    }
}
