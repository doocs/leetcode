//package com.funian.algorithm.algorithm;
//
//import java.util.*;
//
//public class Test {
//    public int[] two(int[] nums, int taregt) {
//        // 哈希存储
//        Map<Integer, Integer> map = new HashMap<>();
//
//        // 循环遍历
//        for (int i = 0; i < nums.length; i++) {
//            // 补数
//            int complement = taregt - nums[i];
//            if (map.containsKey(complement)) {
//                return new int[] {map.get(complement), i};
//            }
//
//            // 插入哈希表
//            map.put(nums[i], i);
//        }
//        return null;
//    }
//
//    public int[] two1(int[] nums, int taregt) {
//        // 哈希表
//        Map<Integer, Integer> map = new HashMap<>();
//
//        // 循环遍历
//        for (int i = 0; i < nums.length; i++) {
//            // 补数
//            int complement = taregt - nums[i];
//            // 补数判断
//            if (map.containsKey(complement)) {
//                return new int[] {map.get(complement), i};
//            }
//
//            // 插入新数据
//            map.put(nums[i], i);
//        }
//        return new int[];
//    }
//
//    public static int[] two3(int[] nums, int target) {
//        // 哈希表
//        Map<Integer, Integer> map = new HashMap<>();
//
//        // 循环
//        for (int i = 0; i < nums.length; i++) {
//            // 补数
//            int complement = target - nums[i];
//            // 判断
//            if (map.containsKey(complement)) {
//                // 返回
//                return new int[] {map.get(complement), i};
//            }
//            // 插入数据和索引
//            map.put(nums[i], i);
//        }
//        // 返回空
//        return null;
//    }
//
//    /**
//     * 字母分组
//     * @param strs
//     * @return
//     */
//    public static List<List<String>> group(String[] strs) {
//        // 哈希表
//        Map<String, List<String>> map = new HashMap<>();
//
//        //遍历
//        for (String str : strs) {
//            // 字符数组
//            char[] chars = str.toCharArray();
//            // 排序
//            Arrays.sort(chars);
//
//            // key设定
//            String key = new String(chars);
//
//            // 判断
//            if (!map.containsKey(key)) {
//                map.put(key, new ArrayList<>());
//            }
//            // 获取
//            map.get(key).add(str);
//
//        }
//        return new ArrayList<>(map.values());
//    }
//
//
//    /**
//     * 字母分组
//     * @param strs
//     * @return
//     */
//    public static List<List<String>> gruopp(String[] strs) {
//        // 哈希表
//        Map<String, List<String>> map = new HashMap<>();
//        // 遍历
//        for (String str : strs) {
//            // 字符数组
//            char[] chars = str.toCharArray();
//
//            // 排序
//            Arrays.sort(chars);
//
//            // key设置
//            String key = new String(chars);
//
//            // 判断
//            if (!map.containsKey(key)) {
//                map.put(key, new ArrayList<>());
//            }
//
//            map.get(key).add(str);
//        }
//        return new ArrayList<>(map.values());
//    }
//
//    public static int longggg(int[] nums) {
//        int n = nums.length;
//        // 去重
//        Set<Integer> setNum = new HashSet<>();
//
//        // 遍历
//        for (int num : nums) {
//            setNum.add(num);
//        }
//
//        // 定义最长
//        int lllll = 0;
//
//        for (int num : setNum) {
//            if (!setNum.contains(num - 1)) {
//                int currN = num;
//                int curr = 1;
//                while (setNum.contains(currN + 1)){
//                    currN++;
//                    curr++;
//                }
//
//                // 更新长度
//                lllll = Math.max(lllll，curr);
//            }
//        }
//        return lllll;
//    }
//
//
//    public int longestConsecutive(int[] nums) {
//        int n = nums.length;
//        // 去重
//        Set<Integer> setNum = new HashSet<>();
//
//        // 遍历
//        for (int num : nums) {
//            setNum.add(num);
//        }
//
//        // 定义最长
//        int lllll = 0;
//
//        for (int num : setNum) {
//            if (!setNum.contains())
//        }
//    }
//
//    public int llosng(int[] nums) {
//        // 哈希去重
//        Set<Integer> setNum = new HashSet<>();
//
//        for (int num : nums) {
//            setNum.add(num);
//        }
//
//        int length = 0;
//        // 遍历去重
//        for (int num : setNum) {
//            if (!setNum.contains(num - 1)) {
//                // 当前长度和元素
//                int currentnum = num;
//                int currentLength = 1;
//                // 循环
//                while (setNum.contains(currentnum + 1)) {
//                    currentLength++;
//                    currentnum++;
//                }
//                // 更新最大长度
//                length = Math.max(length, currentLength);
//            }
//        }
//
//        return length;
//    }
//
//    public int longest(int[] nums) {
//        // 哈希去重
//        Set<Integer> settt = new HashSet<>();
//
//        // 遍历
//        for (int num : nums) {
//            settt.add(num);
//        }
//
//        // 定义
//        int maxL = 0;
//        // 遍历
//        for (int num : settt) {
//            if (!settt.contains(num - 1)) {
//                int currentNum = num;
//                int currentLength = 1;
//                while (settt.contains(currentNum + 1)) {
//                    currentLength++;
//                    currentNum++;
//                }
//                maxL = Math.max(maxL, currentLength);
//            }
//        }
//        return maxL;
//    }
//
//    /**
//     * 双指针
//     * @param nums
//     */
//    public void moveZero(int[] nums) {
//        int n = nums.length;
//        int left = 0;
//        for (int right = 0; right < n; right++) {
//            if (nums[right] != 0) {
//                int temp = nums[left];
//                nums[left] = nums[right];
//                nums[right] = temp;
//                left++;
//            }
//        }
//    }
//
//    public void moveZeroes(int[] nums) {
//        int n = nums.length;
//        int left = 0;
//        for (int right = 0; right < n; right++) {
//            if (nums[right] != 0) {
//                int temp = nums[left];
//                nums[left] = nums[right];
//                nums[right] = temp;
//                left++;
//            }
//        }
//    }
//
//    /**
//     * 移动0
//     * @param nums
//     */
//    public void moveZero(int[] nums) {
//        int n = nums.length;
//        // 定义左指针
//        int left = 0;
//        // 遍历
//        for (int right = 0; right < n; right++) {
//            if (nums[right] != 0) {
//                // 交换
//                int temp = nums[left];
//                nums[left] = nums[right];
//                nums[right] = temp;
//                left++;
//            }
//        }
//    }
//
//    public int maxWater(int[] height) {
//        // 定义左右指针
//        int n = height.length;
//
//        int left = 0;
//        int right = n - 1;
//        int maxWater = 0;
//        // 遍历
//        while (left < right) {
//            int minHeight = Math.min(height[left], height[right]);
//            int currentWater = (right - left) * minHeight;
//            maxWater = Math.max(maxWater, currentWater);
//            if (height[left] < height[right]) {
//                left++;
//            } else {
//                right--;
//            }
//        }
//        return maxWater;
//    }
//
//    public int mammamax(int[] nums) {
//        // 定义左右指针
//        int n = nums.length;
//        int max = 0;
//        int left = 0;
//        int right = n - 1;
//        // 遍历
//        while (left < right) {
//            int min = Math.min(nums[left], nums[right]);
//            int width = right - left;
//            // 当前容量
//            int currentWater = min * width;
//            // 更新最大值
//            max = Math.max(max, currentWater);
//            if (nums[left] < nums[right]) {
//                left++;
//            } else {
//                right--;
//            }
//        }
//        return max;
//    }
//
//    /**
//     * 盛水
//     * @param nums
//     * @return
//     */
//    public int maxWint(int[] nums) {
//        int n = nums.length;
//
//        // 定义指针
//        int left = 0;
//        int right = n - 1;
//        int max = 0;
//
//        // 遍历
//        while (left < right) {
//            int min = Math.min(nums[left], nums[right]);
//            int width = right - left;
//            // 当前容量
//            int currentWater = min * width;
//            // 更新最大值
//
//            max = Math.max(max, currentWater);
//
//            if (nums[left] < nums[right]) {
//                left++;
//            } else {
//                right--;
//            }
//        }
//        return max;
//    }
//
//    public List<List<Integer>> three(int[] nums) {
//        // 定义结果
//        List<List<Integer>> result = new ArrayList<>();
//
//        // 排序
//        Arrays.sort(nums);
//
//        int n = nums.length;
//
//        for (int i = 0; i < n - 2; i++) {
//            if (i > 0 && nums[i] == nums[i - 1]) {
//                continue;
//            }
//
//            int left = i + 1;
//            int right = n - 1;
//            // 遍历
//            while (left < right) {
//                int sum = nums[i] + nums[left] + nums[right];
//                if (sum == 0) {
//                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
//                    while (left < right && nums[left] == nums[left + 1]) {
//                        left++;
//                    }
//
//                    while (left < right && nums[right] == nums[right - 1]) {
//                        right--;
//                    }
//
//                    left++;
//                    right--;
//                } if (sum < 0) {
//                    left++;
//                } else {
//                    right--;
//                }
//            }
//        }
//        return result;
//    }
//
//
//    public List<List<Integer>> three(int[] nums) {
//        List<List<Integer>> result = new ArrayList<>();
//        // 排序
//        Arrays.sort(nums);
//        int n = nums.length;
//        // 遍历
//        for (int i = 0; i < n - 2; i++) {
//            // 去重
//            if (i > 0 && nums[i] == nums[i - 1]) {
//                continue;
//            }
//            // 定义指针
//            int left = i + 1;
//            int right = n - 1;
//            // 指针循环
//            while (left < right) {
//                int sum = nums[i] + nums[left] + nums[right];
//                if (sum == 0) {
//                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
//                    // 去重
//                    while (left < right && nums[left] == nums[left + 1]) {
//                        left++;
//                    }
//                    // 去重
//                    while (left < right && nums[right] == nums[right - 1]) {
//                        right--;
//                    }
//                    left++;
//                    right--;
//                } else if (sum < 0)  {
//                    left++;
//                } else {
//                    right--;
//                }
//            }
//        }
//
//        return result;
//    }
//
//
//    public int trap(int[] nums) {
//        int n = nums.length;
//        // 定义指针
//        int left = 0;
//        int right = n - 1;
//        // 定义最大值
//        int leftMax = 0;
//        int rightMax = 0;
//        int res = 0;
//        // 遍历
//        while (left < right) {
//            leftMax = Math.max(leftMax, nums[left]);
//            rightMax = Math.max(rightMax, nums[right]);
//            if (nums[left] < nums[right]) {
//                res += leftMax - nums[left];
//                left++;
//            } else {
//                res += rightMax - nums[right];
//                right--;
//            }
//        }
//        return res;
//    }
//
//    /**
//     * 最长不重复子串
//     * @param s
//     * @return
//     */
//    public int lengrrh(String s) {
//        int n = s.length();
//        // 去重
//        Set<Character> set = new HashSet<>();
//        // 滑动窗
//        int left = 0;
//        int right = 0;
//        int maxL = 0;
//        // 滑动窗口
//        while (right < n) {
//            if (!set.contains(s.charAt(right))) {
//                set.add(s.charAt(right));
//                right++;
//                maxL = Math.max(maxL, right - left);
//            } else {
//                set.remove(s.charAt(left));
//                left++;
//            }
//        }
//
//        return maxL;
//    }
//
//    /**
//     * 最长不重复子串
//     * @param s
//     * @return
//     */
//    public int longstetet(String s) {
//        // 滑动窗口
//        int left = 0;
//        int n = s.length();
//        int right = 0;
//        int maxL = 0;
//        Set<Character> set = new HashSet<>();
//        while (right < n) {
//            if (!set.contains(s.charAt(right))) {
//                set.add(s.charAt(right));
//                right++;
//                maxL = Math.max(maxL, right - left);
//            }
//            else {
//                set.remove(s.charAt(left));
//                left++;
//            }
//        }
//        return maxL;
//    }
//
//    public List<Integer> findd(String s, String p) {
//        // 定义结果
//        List<Integer> result = new ArrayList<>();
//        if (s == null || s.length() < p.length()) {
//            return result;
//        }
//
//        int[] pc = new int[26];
//        int[] sc = new int[26];
//
//        int pl = p.length();
//        int sl = s.length();
//        for (int i = 0; i < pl; i++) {
//            pc[p.charAt(i) - 'a']++;
//        }
//
//        // 遍历
//        for (int i = 0; i < sl; i++) {
//            sc[s.charAt(i) - 'a']++;
//            if (i >= pl) {
//                sc[s.charAt(i - pl) - 'a']--;
//            }
//
//            if (Arrays.equals(pc, sc)) {
//                result.add(i - pl + 1);
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 子数组和为k的个数
//     * @param nums
//     * @param k
//     * @return
//     */
//    public int suaaa(int[] nums, int k) {
//        // 定义前缀和
//        Map<Integer, Integer> pre =  new HashMap<>();
//
//        // 添加
//        pre.put(0, 1);
//        // 定义当前和
//        int cursum = 0;
//        // 添加
//        int count = 0;
//        // 遍历
//        for (int i = 0; i < nums.length; i++) {
//            cursum += nums[i];
//            // 判断
//            if (pre.containsKey(cursum - k)) {
//                count += pre.get(cursum - k);
//            }
//            // 添加
//            pre.put(cursum, pre.getOrDefault(cursum, 0) + 1);
//        }
//        //
//        return count;
//
//    }
//
//    /**
//     * 滑动窗口
//     * @param nums
//     * @param k
//     * @return
//     */
//    public int[] maxSlding(int[] nums, int k) {
//        int n = nums.length;
//        // 定义结果
//        int[] res = new int[n - k + 1];
//        // 边界条件
//        if (nums == null || nums.length == 0 || k == 0) {
//            return res;
//        }
//
//        // 定义双端队列
//        Deque<Integer> deque = new LinkedList<>();
//        // 遍历
//        for (int i = 0; i < n ; i++) {
//            // 移除滑动窗口外的元素索引
//            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
//                deque.pollFirst();
//            }
//            // 移除所有小于当前元素的队列尾部元素
//            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
//                deque.pollLast();
//            }
//            // 添加当前元素下标到队列尾部
//            deque.offerLast(i);
//            // 记录当前窗口的最大值
//            if (i >= k - 1) {
//                // 队列头部索引对应的元素就是当前窗口的最大值
//                res[i - k + 1] = nums[deque.peekFirst()];
//            }
//        }
//        return res;
//    }
//
//    public static String minWindow(String s, String t) {
//        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
//            return "";
//        }
//
//        // 记录 t 中每个字符的需求数量
//        Map<Character, Integer> targetMap = new HashMap<>();
//        for (char c : t.toCharArray()) {
//            targetMap.put(c, targetMap.getOrDefault(c, 0) + 1);
//        }
//
//        // 滑动窗口所需的变量
//        Map<Character, Integer> windowMap = new HashMap<>();
//        int left = 0, right = 0; // 左右指针，表示窗口的范围
//        int valid = 0; // 符合条件的字符数
//        int minLen = Integer.MAX_VALUE; // 最小长度
//        int start = 0; // 最小窗口的起始位置
//
//        while (right < s.length()) {
//            char c = s.charAt(right); // 获取当前右边界字符
//            right++; // 扩展右边界
//
//            // 如果该字符在 t 中，则加入窗口进行处理
//            if (targetMap.containsKey(c)) {
//                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
//                // 如果窗口中的该字符数量和 t 中需求一致，则有效字符数 +1
//                if (windowMap.get(c).equals(targetMap.get(c))) {
//                    valid++;
//                }
//            }
//
//            // 当窗口内的字符已经满足 t 中所有字符时，尝试收缩左边界
//            while (valid == targetMap.size()) {
//                // 更新最小窗口长度
//                if (right - left < minLen) {
//                    minLen = right - left;
//                    start = left;
//                }
//
//                char d = s.charAt(left); // 获取左边界字符
//                left++; // 收缩左边界
//
//                // 如果该字符在 t 中，收缩窗口时需要进行处理
//                if (targetMap.containsKey(d)) {
//                    // 如果该字符数量减少到不再满足需求，则有效字符数 -1
//                    if (windowMap.get(d).equals(targetMap.get(d))) {
//                        valid--;
//                    }
//                    windowMap.put(d, windowMap.get(d) - 1); // 更新窗口中的字符数量
//                }
//            }
//        }
//
//        // 如果 minLen 没有被更新过，说明没有找到符合条件的子串，返回空字符串
//        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
//    }
//
//
//    /**
//     * 连续子数组的最大和
//     * @param nums
//     * @return
//     */
//    public int subsum(int[] nums) {
//        int cur = nums[0];
//        int max = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            cur = Math.max(nums[i], cur + nums[i]);
//            max = Math.max(max, cur);
//        }
//        return max;
//    }
//
//    public int mama(int[] nums) {
//        int cur = nums[0];
//        int max = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            cur = Math.max(nums[i], cur + nums[i]);
//            max = Math.max(max, cur);
//        }
//    }
//
//    public int max(int[] nums) {
//        int curN = nums[0];
//        int maxN = nums[0];
//        for (int i = 1; i < nums.length; i++) {
//            curN = Math.max(nums[i], curN + nums[i]);
//            maxN = Math.max(maxN, curN);
//        }
//        return maxN;
//    }
//
//    public int[][] merge(int[][] intervals) {
//        if (intervals.length == 0) return new int[0][0];
//        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
//        List<int[]> merged = new ArrayList<>();
//        merged.add(intervals[0]);
//        for (int i = 1; i < intervals.length; i++) {
//            int[] cur = intervals[i];
//            int[] last = merged.get(merged.size() - 1);
//            if (cur[0] <= last[1]) {
//                last[1] = Math.max(last[1], cur[1]);
//            } else {
//                merged.add(cur);
//            }
//        }
//
//        return merged.toArray(new int[merged.size()][]);
//    }
//
//
//    public static void reverse(int[] nums, int start, int end) {
//        while (start < end) {
//            int temp = nums[start];
//            nums[start] = nums[end];
//            nums[end] = temp;
//            start++;
//            end--;
//        }
//    }
//    public static void re(int[] nums, int start, int end) {
//        while (start < end) {
//            int temp = nums[start];
//            nums[start] = nums[end];
//            nums[end] = temp;
//            start++;
//            end--;
//        }
//    }
//
//    public static void rotate(int[] nums, int k) {
//        int n = nums.length;
//        k %= n;
//        reverse(nums, 0, n - 1);
//        reverse(nums, 0, k - 1);
//        reverse(nums, k, n - 1);
//    }
//
//    public static void rotate1(int[] nums, int k) {
//        int n = nums.length;
//        k %= n;
//        re(nums, 0, n - 1);
//        re(nums, 0, k - 1);
//        re(nums, k, n - 1);
//    }
//
//    public static int[] prroo(int[] nums) {
//        int n = nums.length;
//        int[] res = new int[n];
//        res[0] = 1;
//        for (int i = 1; i < n; i++) {
//            res[i] = res[i - 1] * nums[i - 1];
//        }
//        int right = 1;
//        for (int i = n - 1; i >= 0; i--) {
//            res[i] *= right;
//            right *= nums[i];
//        }
//        return res;
//    }
//
//    public int mis(int[] nums) {
//        int n = nums.length;
//        // 遍历数组，将每个元素放到正确的位置
//        for (int i = 0; i < n; i++) {
//            while (nums[i] > 0 && nums[i] <= n && nums[i] != nums[nums[i] - 1]) {
//                swap(nums, i, nums[i] - 1);
//            }
//        }
//        // 找到第一个不匹配的数字
//        for (int i = 0; i < n; i++) {
//            if (nums[i] != i + 1) {
//                return i + 1;
//            }
//        }
//        //  如果没有缺失的数字，返回 n + 1
//        return n + 1;
//    }
//
//    /**
//     * 交换两个元素
//     */
//    public static void swap(int[] nums, int i, int j) {
//        int temp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = temp;
//    }
//
//
//    public void setzeros(int[][] matrix) {
//        int m = matrix.length;
//        int n = matrix[0].length;
//
//        boolean firstRowZero = false;
//        boolean firstColZero = false;
//        for (int i = 0; i < m; i++) {
//            if (matrix[i][0] == 0) {
//                firstColZero = true;
//                break;
//            }
//        }
//        for (int j = 0; j < n; j++) {
//            if (matrix[0][j] == 0) {
//                firstRowZero = true;
//                break;
//            }
//        }
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (matrix[i][j] == 0) {
//                    matrix[i][0] = 0;
//                    matrix[0][j] = 0;
//                }
//            }
//        }
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
//                    matrix[i][j] = 0;
//                }
//            }
//        }
//
//
//        if (firstRowZero) {
//            for (int j = 0; j < n; j++) {
//                matrix[0][j] = 0;
//            }
//        }
//        if (firstColZero) {
//            for (int i = 0; i < m; i++) {
//                matrix[i][0] = 0;
//            }
//        }
//    }
//
//    public static List<Integer> spiralOrder(int[][] matrix) {
//        List<Integer> result = new ArrayList<>();
//
//        if (matrix == null || matrix.length == 0) {
//            return result;
//        }
//
//        int m = matrix.length;
//        int n = matrix[0].length;
//        int top = 0, bottom = m - 1;
//        int left = 0, right = n - 1;
//
//        // 模拟螺旋顺序遍历
//        while (top <= bottom && left <= right) {
//            // 从左到右遍历上边界
//            for (int i = left; i <= right; i++) {
//                result.add(matrix[top][i]);
//            }
//            top++;  // 上边界下移
//
//            // 从上到下遍历右边界
//            for (int i = top; i <= bottom; i++) {
//                result.add(matrix[i][right]);
//            }
//            right--;  // 右边界左移
//
//            // 从右到左遍历下边界
//            if (top <= bottom) {
//                for (int i = right; i >= left; i--) {
//                    result.add(matrix[bottom][i]);
//                }
//                bottom--;  // 下边界上移
//            }
//
//            // 从下到上遍历左边界
//            if (left <= right) {
//                for (int i = bottom; i >= top; i--) {
//                    result.add(matrix[i][left]);
//                }
//                left++;  // 左边界右移
//            }
//        }
//
//        return result;
//    }
//
//
//    public static boolean searchMatrix(int[][] matrix, int target) {
//        // 获取矩阵的行数和列数
//        int m = matrix.length;
//        int n = matrix[0].length;
//
//        // 从矩阵的右上角开始搜索
//        int row = 0;
//        int col = n - 1;
//
//        // 当行和列都在合理范围内时，进行搜索
//        while (row < m && col >= 0) {
//            if (matrix[row][col] == target) {
//                // 找到目标值
//                return true;
//            } else if (matrix[row][col] > target) {
//                // 当前元素大于目标值，向左移动一列
//                col--;
//            } else {
//                // 当前元素小于目标值，向下移动一行
//                row++;
//            }
//        }
//
//        // 如果遍历完整个矩阵仍未找到目标值，返回 false
//        return false;
//    }
//
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        // 使用哈希集合存储链表A中的节点
//        Set<ListNode> visited = new HashSet<ListNode>();
//        // 定义临时变量，用于遍历链表A
//        ListNode temp = headA;
//        // 遍历链表A，将每个节点存入哈希集合
//        while (temp != null) {
//            visited.add(temp);  // 将当前节点加入集合
//            temp = temp.next;   // 移动到下一个节点
//        }
//        // 重置temp为链表B的头节点，开始遍历链表B
//        temp = headB;
//        // 遍历链表B，查找第一个与链表A相交的节点
//        while (temp != null) {
//            if (visited.contains(temp)) {  // 如果链表B的节点在链表A的集合中出现过，说明这是相交点
//                return temp;  // 返回相交点
//            }
//            temp = temp.next;  // 继续遍历链表B的下一个节点
//        }
//        // 如果没有找到相交节点，返回null
//        return null;
//    }
//
//    public static class ListNode {
//        int val;
//        ListNode next;
//
//        ListNode(int x) {
//            val = x;
//        }
//    }
//
//    public static ListNode reverseList(ListNode head) {
//        ListNode prev = null;
//        ListNode curr = head;
//
//        while (curr != null) {
//            ListNode next = curr.next; // 暂存下一个节点
//            curr.next = prev;          // 当前节点的next指向前一个节点
//            prev = curr;               // prev前移
//            curr = next;               // curr前移
//        }
//
//        return prev;  // 返回新链表的头节点
//    }
//
//
//    public static ListNode revr(ListNode head) {
//        ListNode prev = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode next = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = next;
//        }
//        return prev;
//    }
//
//
//
//    public boolean isPalindrome(ListNode head) {
//        if (head == null) return true;
//
//        // 使用快慢指针找到链表的中间节点
//        ListNode firstHalfEnd = endOfFirstHalf(head);
//        ListNode secondHalfStart = reverseList(firstHalfEnd.next);
//
//        // 检查链表是否回文
//        ListNode p1 = head;
//        ListNode p2 = secondHalfStart;
//        boolean result = true;
//        while (result && p2 != null) {
//            if (p1.val != p2.val) {
//                result = false;
//            }
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//
//        // 恢复链表（可选）
//        firstHalfEnd.next = reverseList(secondHalfStart);
//
//        return result;
//    }
//
//    // 反转链表的方法
//    private ListNode reverseList(ListNode head) {
//        ListNode prev = null;
//        ListNode curr = head;
//        while (curr != null) {
//            ListNode next = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = next;
//        }
//        return prev;
//    }
//
//    // 使用快慢指针找到链表的中间节点
//    private ListNode endOfFirstHalf(ListNode head) {
//        ListNode fast = head;
//        ListNode slow = head;
//        while (fast.next != null && fast.next.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//        }
//        return slow;
//    }
//
//    public boolean hasCycle(ListNode head) {
//        if (head == null) return false;
//        ListNode slow = head;
//        ListNode fast = head;
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//            if (slow == fast) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public ListNode detectCycle(ListNode head) {
//        if (head == null || head.next == null) return null;
//
//        // 1. 快慢指针寻找相遇点（若无环则返回 null）
//        ListNode slow = head, fast = head;
//        boolean hasCycle = false;
//        while (fast != null && fast.next != null) {
//            slow = slow.next;
//            fast = fast.next.next;
//            if (slow == fast) {
//                hasCycle = true;
//                break;
//            }
//        }
//        if (!hasCycle) return null;
//
//        // 2. 将一个指针移回链表头，两指针同速前进，相遇处即为入环节点
//        ListNode p1 = head, p2 = slow;
//        while (p1 != p2) {
//            p1 = p1.next;
//            p2 = p2.next;
//        }
//        return p1;
//    }
//
//
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        // 创建哨兵节点，简化边界条件的处理
//        ListNode dummy = new ListNode(0);
//        ListNode current = dummy;
//
//        // 遍历两个链表，直到其中一个为空
//        while (l1 != null && l2 != null) {
//            if (l1.val <= l2.val) {
//                current.next = l1;
//                l1 = l1.next;
//            } else {
//                current.next = l2;
//                l2 = l2.next;
//            }
//            current = current.next;
//        }
//
//        // 将剩余节点链接到结果链表中
//        if (l1 != null) {
//            current.next = l1;
//        } else {
//            current.next = l2;
//        }
//
//        // 返回合并后的链表的头节点
//        return dummy.next;
//    }
//
//    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        ListNode dummy = new ListNode(0); // 哑节点，用于简化链表操作
//        ListNode current = dummy; // 当前节点指针
//        int carry = 0; // 进位值
//
//        // 遍历两个链表
//        while (l1 != null || l2 != null || carry != 0) {
//            int sum = carry; // 初始化和为进位
//
//            // 如果 l1 不为空，则加上 l1 的值
//            if (l1 != null) {
//                sum += l1.val;
//                l1 = l1.next; // 移动到下一个节点
//            }
//
//            // 如果 l2 不为空，则加上 l2 的值
//            if (l2 != null) {
//                sum += l2.val;
//                l2 = l2.next; // 移动到下一个节点
//            }
//
//            carry = sum / 10; // 计算新的进位
//            current.next = new ListNode(sum % 10); // 创建新节点，保存当前位的值
//            current = current.next; // 移动到下一个节点
//        }
//
//        return dummy.next; // 返回结果链表
//    }
//
//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        // 创建一个虚拟头节点（dummy node），便于处理边界情况
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//
//        // 初始化两个指针，start和end，均指向虚拟头节点
//        ListNode start = dummy;
//        ListNode end = dummy;
//
//        // 将end指针向前移动n+1步，以便使start和end之间的距离为n
//        for (int i = 0; i <= n; i++) {
//            end = end.next;
//        }
//
//        // 将start和end指针同时向前移动，直到end指针到达链表末尾
//        while (end != null) {
//            start = start.next;
//            end = end.next;
//        }
//
//        // 删除start.next指向的节点
//        start.next = start.next.next;
//
//        // 返回虚拟头节点的下一个节点，即新的链表头
//        return dummy.next;
//    }
//
//    public ListNode swapPairs(ListNode head) {
//        // 虚拟头
//        ListNode dummy = new ListNode(0);
//        dummy.next = head;
//
//        // prev 指向待交换对的前驱
//        ListNode prev = dummy;
//        while (prev.next != null && prev.next.next != null) {
//            // 待交换的两个节点
//            ListNode a = prev.next;
//            ListNode b = a.next;
//
//            // 交换：prev->b->a->b.next
//            prev.next = b;
//            a.next = b.next;
//            b.next = a;
//
//            // 移动 prev 到下一对的前驱
//            prev = a;
//        }
//
//        return dummy.next;
//    }
//
//
//    public int find(int[] nums) {
//        int slow = nums[0];
//        int fast = nums[0];
//        do {
//            slow = nums[slow];
//            fast = nums[nums[fast]];
//        } while (slow != fast);
//
//        slow = nums[0];
//        while (slow != fast) {
//            slow = nums[slow];
//            fast = nums[fast];
//        }
//        return  slow;
//    }
//
//    public int find(int[] nums) {
//        int left = 0;
//        int right = nums.length - 1;
//        while (left < right) {
//            int mid = left + (right - left) / 2;
//
//            int count = 0;
//            for (int i = 0; i < nums.length; i++) {
//                if (nums[i] <= mid) {
//                    count++;
//                }
//            }
//            if (count <= mid) {
//                left = mid + 1;
//            } else {
//                right = mid;
//            }
//        }
//        return left;
//    }
//
//
//
//    public void next(int[] nums) {
//        int n = nums.length;
//        int i = n - 2;
//        while (i >= 0 && nums[i] >= nums[i + 1]) {
//            i--;
//        }
//
//        if (i >= 0) {
//            int j = n - 1;
//            while (nums[j]<= nums[i]) {
//                j--;
//            }
//            swap(nums, i, j);
//        }
//
//        re(nums, i + 1, n - 1);
//    }
//
//    public void next(int[] nums) {
//        // 下一个
//        int n = nums.length;
//        int i = n - 2;
//        while (i >= 0) && nums[i] >= nums[i + 1]) {
//            i--;
//        }
//
//        if (i >= 0) {
//            int j = n - 1;
//            while (nums[j] <= nums[i]) {
//                j--;
//
//                swap(nums, i, j);
//            }
//        }
//
//        re(nums, i +1 , n - 1);
//    }
//
//
//    public void sortColor(int[] nums) {
//        int zero = 0;
//        int two = nums.length - 1;
//        int cur = 0;
//        while (cur <= two) {
//            if (nums[cur] == 0) {
//                swap(nums, cur, zero);
//                zero++;
//                cur++;
//            } else if (nums[cur] == 2) {
//                swap(nums, cur, two);
//                two--;
//            } else {
//                cur++;
//            }
//        }
//    }
//
//    public int fnnfnfn(int[] nums) {
//        int can = 0;
//        int count = 0;
//        for (int num : nums) {
//            if (count == 0) {
//                can = num;
//            }
//            if (can == num) {
//                count++;
//            } else {
//                count--;
//            }
//        }
//        return can;
//    }
//
//    public int uqueu(int[] nums) {
//        int unique = 0;
//        for (int num : nums) {
//            unique ^= num;
//        }
//        return unique;
//    }
//
//    public int min change(String s1, String s2) {
//        int m = s1.length();
//        int n = s2.length();
//
//        int[][] dp = new int[m + 1][n + 1];
//
//        for (int i = 0; i <= m; i++) {
//            dp[i][0] = i;
//        }
//
//        for (int i = 0; i <= n ; i++) {
//            dp[0][i] = i;
//        }
//
//        for (int i = 1; i <=  m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1];
//                } else {
//                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
//                }
//            }
//        }
//
//        return dp[m][n];
//    }
//
//    public int lohshheh(String s1, String s2) {
//        int m = s1.length();
//        int n = s2.length();
//        int[][] dp = new int[m + 1][n + 1];
//        for (int i = 1; i <= m; i++) {
//            for (int j = 1; j <= n; j++) {
//                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
//                    dp[i][j] = dp[i - 1][j - 1] + 1;
//                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
//                }
//            }
//        }
//        return dp[m][n];
//
//    }
//
//    public String longggs(String s) {
//        int n = s.length();
//        if (n == 0 || n == 1) return s;
//
//        int start = 0;
//        int end = 0;
//        for (int i = 0; i < n; i++) {
//
//            int len1 = expandAroundCenter(s, i, i);
//            int len2 = expandAroundCenter(s, i, i + 1);
//            int len = Math.max(len1, len2);
//            if (len > end - start) {
//                start = i - (len - 1) / 2;
//                end = i + len / 2;
//            }
//            return s.substring(start, end + 1);
//        }
//    }
//
//    public static int expandAroundCenter(String s, int left, int right) {
//        int L = left;
//        int R = right;
//        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
//            L--;
//            R++;
//        }
//        return R - L - 1;
//    }
//
//
//    public int minPath(int[][] grid) {
//        if (grid == null || grid.length == 0) return 0;
//        int m = grid.length;
//        int n = grid[0].length;
//        int[][] dp = new int[m][n];
//        dp[0][0] = grid[0][0];
//        for (int i = 1; i < m; i++) {
//            dp[i][0] = dp[i - 1][0] + grid[i][0];
//        }
//        for (int j = 1; j < n; j++) {
//            dp[0][j] = dp[0][j - 1] + grid[0][j];
//        }
//
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
//            }
//        }
//
//        return dp[m - 1][n - 1];
//    }
//
//
//    public int uque(int m, int n) {
//        int[][] dp = new int[m ][n];
//        for (int i = 0; i < m; i++) {
//            dp[i][0] = 1;
//        }
//        for (int j = 0; j < n; j++) {
//            dp[0][j] = 1;
//        }
//        for (int i = 1; i < m; i++) {
//            for (int j = 1; j < n; j++) {
//                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
//            }
//        }
//        return dp[m - 1][n - 1];
//    }
//
//    public int longestValidParenthesesStack(String s) {
//        if (s == null || s.length() <= 1) return 0;
//
//        Stack<Integer> stack = new Stack<>();
//        // 栈底初始化为-1，作为边界
//        stack.push(-1);
//        int maxLength = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            if (s.charAt(i) == '(') {
//                // 遇到'('，将其下标入栈
//                stack.push(i);
//            } else {
//                // 遇到')'，弹出栈顶元素
//                stack.pop();
//
//                if (stack.isEmpty()) {
//                    // 如果栈为空，说明当前')'无法匹配
//                    // 将其下标入栈作为新的边界
//                    stack.push(i);
//                } else {
//                    // 如果栈不为空，计算当前有效长度
//                    int currentLength = i - stack.peek();
//                    maxLength = Math.max(maxLength, currentLength);
//                }
//            }
//        }
//
//        return maxLength;
//    }
//
//    public boolean canpa(int[] nums) {
//        int sum = 0;
//        for (int num : nums) {
//            sum += num;
//        }
//        if (sum % 2 != 0) {
//            return false;
//        }
//        int target = sum / 2;
//        boolean[] dp = new boolean[target + 1];
//        dp[0] = true;
//
//        for (int num : nums) {
//            for (int i = target; i >= num; i--) {
//                dp[i] = dp[i] || dp[i - num];
//            }
//        }
//        return dp[target];
//    }
//
//    public int maxProductStandard(int[] nums) {
//        if (nums == null || nums.length == 0) return 0;
//
//        int maxProduct = nums[0];
//        int minProduct = nums[0];
//        int result = nums[0];
//
//        for (int i = 1; i < nums.length; i++) {
//            // 计算三种可能的乘积
//            int currentMax = maxProduct * nums[i];
//            int currentMin = minProduct * nums[i];
//
//            // 更新最大乘积和最小乘积
//            maxProduct = Math.max(nums[i], Math.max(currentMax, currentMin));
//            minProduct = Math.min(nums[i], Math.min(currentMax, currentMin));
//
//            // 更新全局最大值
//            result = Math.max(result, maxProduct);
//        }
//
//        return result;
//    }
//
//
//    public static int lengthOfLIS(int[] nums) {
//        // 边界情况：空数组
//        if (nums.length == 0) return 0;
//
//        // dp[i] 表示以 nums[i] 结尾的最长严格递增子序列的长度
//        int[] dp = new int[nums.length];
//
//        // 初始化 dp 数组
//        // 每个元素自身就是一个长度为 1 的子序列
//        for (int i = 0; i < nums.length; i++) {
//            dp[i] = 1;
//        }
//
//        // 动态规划填充 dp 数组
//        // 外层循环遍历每个元素
//        for (int i = 1; i < nums.length; i++) {
//            // 内层循环检查所有在i之前的元素
//            for (int j = 0; j < i; j++) {
//                // 只考虑严格递增的情况：nums[i] > nums[j]
//                if (nums[i] > nums[j]) {
//                    // 状态转移方程：
//                    // 以nums[i]结尾的最长递增子序列长度 =
//                    // max(当前值, 以nums[j]结尾的最长递增子序列长度 + 1)
//                    dp[i] = Math.max(dp[i], dp[j] + 1);
//                }
//            }
//        }
//
//        // 找到 dp 数组中的最大值，即为最长递增子序列的长度
//        int maxLength = 0;
//        for (int length : dp) {
//            maxLength = Math.max(maxLength, length);
//        }
//
//        return maxLength;
//    }
//
//    public boolean wordBreakOptimized(String s, List<String> wordDict) {
//        // 将字典转换为哈希集合，提高查找效率
//        Set<String> wordSet = new HashSet<>(wordDict);
//
//        // 计算字典中最长单词的长度
//        int maxLength = 0;
//        for (String word : wordDict) {
//            maxLength = Math.max(maxLength, word.length());
//        }
//
//        // dp[i] 表示字符串s的前i个字符能否被拆分
//        boolean[] dp = new boolean[s.length() + 1];
//
//        // 初始化：空字符串可以被拆分
//        dp[0] = true;
//
//        // 动态规划填充DP数组
//        for (int i = 1; i <= s.length(); i++) {
//            // 从i-1开始向前检查，但不超过最长单词长度
//            for (int j = i - 1; j >= 0 && i - j <= maxLength; j--) {
//                // 如果前j个字符可以拆分，且从j到i的子串在字典中，则前i个字符可以拆分
//                if (dp[j] && wordSet.contains(s.substring(j, i))) {
//                    dp[i] = true;
//                    break; // 找到一种可行方案即可退出内层循环
//                }
//            }
//        }
//
//        // 返回整个字符串是否可以被拆分
//        return dp[s.length()];
//    }
//
//    public int[] hhhs9
//}
