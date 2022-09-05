class Solution {
    public boolean judgePoint24(int[] nums) {
        return dfs(Arrays.stream(nums).boxed().map(Double::new).collect(Collectors.toList()));
    }

    private boolean dfs(List<Double> numList) {
        if (numList.size() == 0) {
            return false;
        }
        if (numList.size() == 1) {
            return Math.abs(Math.abs(numList.get(0) - 24.0)) < 0.000001d;
        }
        for (int i = 0; i < numList.size(); i++) {
            for (int j = i + 1; j < numList.size(); j++) {
                boolean valid = dfs(getList(numList, i, j, 0)) || dfs(getList(numList, i, j, 1))
                    || dfs(getList(numList, i, j, 2)) || dfs(getList(numList, i, j, 3))
                    || dfs(getList(numList, i, j, 4)) || dfs(getList(numList, i, j, 5));
                if (valid) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Double> getList(List<Double> numList, int i, int j, int operate) {
        List<Double> candidate = new ArrayList<>();
        for (int k = 0; k < numList.size(); k++) {
            if (k == i || k == j) {
                continue;
            }
            candidate.add(numList.get(k));
        }

        switch (operate) {
        // a + b
        case 0:
            candidate.add(numList.get(i) + numList.get(j));
            break;
        // a - b
        case 1:
            candidate.add(numList.get(i) - numList.get(j));
            break;
        // b - a
        case 2:
            candidate.add(numList.get(j) - numList.get(i));
            break;
        // a * b
        case 3:
            candidate.add(numList.get(i) * numList.get(j));
            break;
        // a / b
        case 4:
            if (numList.get(j) == 0) {
                return Collections.emptyList();
            }
            candidate.add(numList.get(i) / numList.get(j));
            break;
        // b / a
        case 5:
            if (numList.get(i) == 0) {
                return Collections.emptyList();
            }
            candidate.add(numList.get(j) / numList.get(i));
            break;
        }
        return candidate;
    }
}