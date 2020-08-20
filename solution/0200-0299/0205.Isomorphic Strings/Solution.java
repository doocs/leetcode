public class Solution {
    public boolean isIsomorphic(String s, String t) {

		if (s == null || t == null) {
			return false;
		}

		if (s.length() != t.length()) {
			return false;
		}

		Map<Character, Character> map = new HashMap<Character, Character>();
		Set<Character> set = new HashSet<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c1 = s.charAt(i);
			char c2 = t.charAt(i);

			if (map.containsKey(c1)) {
				if (map.get(c1) != c2) {
					return false;
				}
			} else {
				if (set.contains(c2)) {
					return false;
				}
				map.put(c1, c2);
				set.add(c2);
			}
		}

		return true;
	
        
    }
}