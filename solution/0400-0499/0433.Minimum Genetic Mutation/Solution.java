class Solution {
    public int minMutation(String start, String end, String[] bank) {
        Set<String> s = new HashSet<>();
        for (String b : bank) {
            s.add(b);
        }
        Map<Character, String> mp = new HashMap<>(4);
        mp.put('A', "TCG");
        mp.put('T', "ACG");
        mp.put('C', "ATG");
        mp.put('G', "ATC");
        Deque<Pair<String, Integer>> q = new LinkedList<>();
        q.offer(new Pair<>(start, 0));
        while (!q.isEmpty()) {
            Pair<String, Integer> p = q.poll();
            String t = p.getKey();
            int step = p.getValue();
            if (end.equals(t)) {
                return step;
            }
            for (int i = 0; i < t.length(); ++i) {
                for (char c : mp.get(t.charAt(i)).toCharArray()) {
                    String next = t.substring(0, i) + c + t.substring(i + 1);
                    if (s.contains(next)) {
                        q.offer(new Pair<>(next, step + 1));
                        s.remove(next);
                    }
                }
            }
        }
        return -1;
    }
}