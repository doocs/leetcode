class Solution {
	public boolean isAnagram(String s, String t) {
		char[] val1 = s.toCharArray();
		char[] val2 = t.toCharArray();
		Arrays.sort(val1);
		Arrays.sort(val2);
		String s1 = new String(val1);
		String s2 = new String(val2);
		return s1.equals(s2);
	}
}
