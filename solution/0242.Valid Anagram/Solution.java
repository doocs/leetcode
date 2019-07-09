import java.util.Arrays;

public class Solution {

	public static void main(String[] args) {

		String s = "anagram";
		String t = "nagaram";

		boolean result = isAnagram(s, t);
		System.out.println(result);
	}

	private static boolean isAnagram(String s, String t) {

		char val1[] = s.toCharArray();
		char val2[] = t.toCharArray();
		Arrays.sort(val1);
		Arrays.sort(val2);
		String s1 = new String(val1);
		String s2 = new String(val2);
		if(s1.equals(s2))
			return true;
		else
			return false;
	}

}
