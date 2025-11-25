package com.funian.algorithm.algorithm;

import java.util.*;

/**
 * 字母分组（LeetCode 49）
 *
 * 时间复杂度：O(N * K * log K)
 * - N 是字符串数组的长度
 * - K 是字符串的最大长度
 * - 对每个字符串进行排序需要 O(K * log K) 时间
 * - 总共需要处理 N 个字符串
 *
 * 空间复杂度：O(N * K)
 * - HashMap 存储所有字符串需要 O(N * K) 空间
 * - 排序时创建的字符数组需要 O(K) 空间
 * - 返回结果列表需要 O(N * K) 空间
 */
public class GroupAnagrams49 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 提示用户输入字符串
        System.out.println("输入字符串：");

        // 读取一行输入
        // 例如用户输入："eat tea tan ate nat bat"
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        // line.split(" ") 将 "eat tea tan ate nat bat" 分割为 ["eat", "tea", "tan", "ate", "nat", "bat"]
        String[] strs = line.split(" ");

        // 调用 groupAnagrams 方法对字母异位词进行分组
        List<List<String>> result = groupAnagrams(strs);

        // 输出分组结果
        System.out.println("分组后的结果为：");

        // 遍历并打印每个分组
        for (List<String> group : result) {
            System.out.println(group);
        }
    }

    /**
     * 字母异位词分组
     * 将字符串数组中互为字母异位词的字符串分到同一组
     * 字母异位词是指两个字符串包含相同的字符，但字符的顺序可能不同
     *
     * 算法思路：
     * 1. 对每个字符串的字符进行排序，异位词排序后结果相同
     * 2. 使用排序后的字符串作为键，将原字符串分组
     * 3. 返回所有分组
     *
     * 示例过程（以数组 ["eat","tea","tan","ate","nat","bat"] 为例）：
     *
     * 字符串  排序后  分组过程
     * "eat"   "aet"   map={"aet": ["eat"]}
     * "tea"   "aet"   map={"aet": ["eat","tea"]}
     * "tan"   "ant"   map={"aet": ["eat","tea"], "ant": ["tan"]}
     * "ate"   "aet"   map={"aet": ["eat","tea","ate"], "ant": ["tan"]}
     * "nat"   "ant"   map={"aet": ["eat","tea","ate"], "ant": ["tan","nat"]}
     * "bat"   "abt"   map={"aet": ["eat","tea","ate"], "ant": ["tan","nat"], "abt": ["bat"]}
     *
     * 时间复杂度分析：
     * - 外层循环遍历N个字符串：O(N)，其中N为输入字符串数组strs的长度
     * - 对每个字符串排序：O(K * log K)，其中K是单个字符串的最大长度
     * - 哈希表操作（插入、查找）：O(1)平均时间
     * - 总时间复杂度：O(N * K * log K)
     *
     * 空间复杂度分析：
     * - HashMap存储所有字符串：O(N * K)，其中N为字符串数量，K为平均字符串长度
     * - 字符数组存储：O(K)，用于排序时的临时存储
     * - 返回结果列表：O(N * K)，存储所有分组结果
     * - 总空间复杂度：O(N * K)
     *
     * @param strs 输入的字符串数组
     * @return 分组后的字母异位词列表
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        // 使用哈希表存储分组结果，键为排序后的字符串，值为该组的所有原始字符串
        Map<String, List<String>> map = new HashMap<>();

        // 遍历每个字符串
        for (String str : strs) {
            // 将字符串转换为字符数组
            char[] chars = str.toCharArray();

            // 对字符数组进行排序，使得异位词具有相同的排序结果
            Arrays.sort(chars);

            // 将排序后的字符数组转换为字符串作为键
            String key = new String(chars);

            // 如果该键不存在，则创建新的列表
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            // 将原始字符串添加到对应键的列表中
            map.get(key).add(str);
        }

        // 返回所有分组的列表
        return new ArrayList<>(map.values());
    }


    /**
     * 方法2：使用字符计数作为键（避免排序）
     *
     * 算法思路：
     * 1. 对每个字符串统计每个字符的出现次数
     * 2. 将字符计数数组转换为唯一字符串作为键
     * 3. 使用该键进行分组
     *
     * 示例过程（以数组 ["eat","tea","tan","ate","nat","bat"] 为例）：
     *
     * 字符串  字符计数               键字符串            分组过程
     * "eat"   [1,0,0,0,1,0,...,1]   "#1#0#0#0#1#0...#1" map={key: ["eat"]}
     * "tea"   [1,0,0,0,1,0,...,1]   "#1#0#0#0#1#0...#1" map={key: ["eat","tea"]}
     * "tan"   [1,0,0,0,0,0,...,2]   "#1#0#0#0#0#0...#2" map={key1: ["eat","tea"], key2: ["tan"]}
     *
     * 时间复杂度分析：
     * - 外层循环遍历N个字符串：O(N)，其中N为输入字符串数组strs的长度
     * - 对每个字符串统计字符：O(K)，其中K为单个字符串的最大长度
     * - 构造键字符串：O(26) = O(1)，固定26个小写字母
     * - 哈希表操作：O(1)平均时间
     * - 总时间复杂度：O(N * K)
     *
     * 空间复杂度分析：
     * - HashMap存储所有字符串：O(N * K)，其中N为字符串数量，K为平均字符串长度
     * - 计数数组：O(26) = O(1)，固定大小的字符计数数组
     * - StringBuilder存储键字符串：O(26) = O(1)，用于构建键字符串
     * - 返回结果列表：O(N * K)，存储所有分组结果
     * - 总空间复杂度：O(N * K)
     *
     * @param strs 输入的字符串数组
     * @return 分组后的字母异位词列表
     */
    public static List<List<String>> groupAnagramsCount(String[] strs) {
        // 使用哈希表存储分组结果，键为字符计数字符串，值为该组的所有原始字符串
        Map<String, List<String>> map = new HashMap<>();

        // 遍历每个字符串
        for (String str : strs) {
            // 统计每个字符的出现次数
            // 创建长度为26的数组，对应26个小写字母a-z
            int[] count = new int[26]; // 初始化为[0,0,0,...,0]

            // 遍历字符串中的每个字符
            for (char c : str.toCharArray()) {
                // 统计字符出现次数
                // c - 'a' 将字符转换为索引（'a'->0, 'b'->1, ..., 'z'->25）
                count[c - 'a']++; // 对应字符计数加1
            }

            // 将计数数组转换为字符串作为键
            // 使用StringBuilder提高字符串拼接效率
            StringBuilder sb = new StringBuilder();

            // 遍历26个字母的计数
            for (int i = 0; i < 26; i++) {
                // 添加分隔符避免计数混淆（例如计数10和1,0的区别）
                sb.append('#');
                // 添加当前字母的计数
                sb.append(count[i]);
            }

            // 将StringBuilder转换为字符串作为哈希表的键
            String key = sb.toString();

            // 分组逻辑同上：如果键不存在则创建新列表，然后添加字符串
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            // map.get(key).add(str) 添加字符串到对应列表
            map.get(key).add(str);
        }

        // 返回所有分组的列表
        return new ArrayList<>(map.values());
    }

    /**
     * 方法3：使用质数乘积作为键
     *
     * 算法思路：
     * 1. 为每个字母分配一个唯一的质数
     * 2. 计算每个字符串中所有字符对应质数的乘积
     * 3. 异位词具有相同的字符组成，因此乘积相同
     * 4. 使用乘积作为键进行分组
     *
     * 数学原理：
     * 根据算术基本定理，每个大于1的自然数都可以唯一地分解为质数的乘积
     * 因此，相同字符组成的字符串（异位词）会产生相同的乘积
     *
     * 示例过程（以数组 ["eat","tea","tan","ate","nat","bat"] 为例）：
     *
     * 字符串  字符对应质数          乘积                分组过程
     * "eat"   2(e) * 3(a) * 5(t)   30                  map={30: ["eat"]}
     * "tea"   5(t) * 2(e) * 3(a)   30                  map={30: ["eat","tea"]}
     * "tan"   5(t) * 3(a) * 7(n)   105                 map={30: ["eat","tea"], 105: ["tan"]}
     *
     * 时间复杂度分析：
     * - 外层循环遍历N个字符串：O(N)，其中N为输入字符串数组strs的长度
     * - 对每个字符串计算乘积：O(K)，其中K为单个字符串的最大长度
     * - 哈希表操作：O(1)平均时间
     * - 总时间复杂度：O(N * K)
     *
     * 空间复杂度分析：
     * - HashMap存储所有字符串：O(N * K)，其中N为字符串数量，K为平均字符串长度
     * - 质数数组：O(26) = O(1)，存储26个质数的固定数组
     * - 返回结果列表：O(N * K)，存储所有分组结果
     * - 总空间复杂度：O(N * K)
     *
     * @param strs 输入的字符串数组
     * @return 分组后的字母异位词列表
     */
    public static List<List<String>> groupAnagramsPrime(String[] strs) {
        // 26个质数对应26个小写字母 a-z
        // 质数的选择确保了不同字符组合的唯一性
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

        // 使用哈希表存储分组结果，键为质数乘积，值为该组的所有原始字符串
        // 使用Long类型避免整数溢出
        Map<Long, List<String>> map = new HashMap<>();

        // 遍历每个字符串
        for (String str : strs) {
            // 初始化乘积为1（乘法的单位元）
            long key = 1;

            // 计算字符串的质数乘积
            // 遍历字符串中的每个字符
            for (char c : str.toCharArray()) {
                // 计算字符对应的质数乘积
                key *= primes[c - 'a'];
            }

            // 分组逻辑：如果键不存在则创建新列表，然后添加字符串
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }

        // 返回所有分组的列表
        return new ArrayList<>(map.values());
    }
}
