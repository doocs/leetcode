class Solution {

    public int findMaximumXOR(int[] numbers) {
        Set<Integer> oneNubmer = new HashSet<>();
        Set<Integer> twoNubmer = new HashSet<>();
        // oneNumber not has number
        int[] oneNumberSet = null;
        int[] twoNumberSet = null;
        boolean flag = true;
        for (int i = 31; i > 0; i--) {
            int mask = 1 << (i - 1);
            for (int j = 0; j < numbers.length; j++) {
                if (flag) {
                    // deal oneNumber
                    if ((mask & numbers[j]) > 0) {
                        oneNubmer.add(numbers[j]);
                    }
                } else {
                    // deal twoNumber
                    if (!twoNubmer.contains(numbers[j])) {
                        for (int k = 0; k < oneNumberSet.length; k++) {
                            if ((mask & (numbers[j] ^ oneNumberSet[k])) > 0) {
                                twoNubmer.add(numbers[j]);
                            }
                        }
                    }
                }
            }
            if (flag && oneNubmer.size() > 0) {
                flag = false;
                oneNumberSet = oneNubmer.stream().mapToInt(Integer::valueOf).toArray();
            }
            if (twoNubmer.size() > 0) {
                twoNumberSet = twoNubmer.stream().mapToInt(Integer::valueOf).toArray();
                break;
            }
        }
        // 处理两种特殊的结果集
        if (oneNubmer.size() == 0) {
            return -1;
        } else {
            if (twoNubmer.size() == 0) {
                return oneNumberSet[0];
            }
        }
        // 获取最大异或结果(也可以得到是哪种组合)
        int count = 0;
        for (int x = 0; x < oneNumberSet.length; x++) {
            for (int y = 0; y < twoNumberSet.length; y++) {
                if ((oneNumberSet[x] ^ twoNumberSet[y]) > count) {
                    count = oneNumberSet[x] ^ twoNumberSet[y];
                }
            }
        }
        return count;
    }
}
