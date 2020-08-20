class Solution {
    public int[] findOrder(int numCourses, int[][] pre) {
    	// key 课程id，value 前置课程id
		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < pre.length; i++) {
			if (map.containsKey(pre[i][0])) {
				map.get(pre[i][0]).add(pre[i][1]);
			} else {
				Set<Integer> set = new HashSet<>();
				set.add(pre[i][1]);
				map.put(pre[i][0], set);
			}
		}

		int[] visit = new int[numCourses];
		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < numCourses; i++) {
			if (!dfs(i, visit, map, ans)) {
				return new int[0];
			}
		}
		
		int[] result = new int[numCourses];
		for(int i = 0; i < numCourses; i++){
            result[i] = ans.get(i);
        }
		
		return result;
    }

	private boolean dfs(int i, int[] visit, Map<Integer, Set<Integer>> map, List<Integer> ans) {
		if (visit[i] == -1) {
			return false;
		}

		if (visit[i] == 1) {
			return true;
		}

		visit[i] = -1;

		if (map.containsKey(i)) {
			for (int pre : map.get(i)) {
				if (!dfs(pre, visit, map,ans)) {
					return false;
				}
			}
		}

		visit[i] = 1;
		ans.add(i);
		return true;
	}
}