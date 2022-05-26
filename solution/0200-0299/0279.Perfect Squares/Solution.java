class Solution {
	public int numSquares(int n) {
		List<Integer> ans = new ArrayList<>();
		ans.add(0);
		while (ans.size() <= n) {
			int m = ans.size(), val = Integer.MAX_VALUE;
			for (int i = 1; i * i <= m; i++) {
				val = Math.min(val, ans.get(m - i * i) + 1);
			}
			ans.add(val);
		}
		return ans.get(n);
	}
}