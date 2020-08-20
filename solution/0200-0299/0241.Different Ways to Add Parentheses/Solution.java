public class Solution {
    public List<Integer> diffWaysToCompute(String input) {

		List<Integer> rt = new LinkedList<Integer>();
		int len = input.length();

		for (int i = 0; i < len; i++) {

			if (input.charAt(i) == '-' || input.charAt(i) == '*'
					|| input.charAt(i) == '+') {

				String part1 = input.substring(0, i);
				String part2 = input.substring(i + 1);

				List<Integer> part1Ret = diffWaysToCompute(part1);
				List<Integer> part2Ret = diffWaysToCompute(part2);

				for (Integer p1 : part1Ret) {
					for (Integer p2 : part2Ret) {
						int c = 0;
						switch (input.charAt(i)) {
						case '+':
							c = p1 + p2;
							break;
						case '-':
							c = p1 - p2;
							break;
						case '*':
							c = p1 * p2;
						}
						rt.add(c);
					}
				}
			}
		}

		if (rt.size() == 0) {
			rt.add(Integer.valueOf(input));
		}

		return rt;
	
        
    }
}