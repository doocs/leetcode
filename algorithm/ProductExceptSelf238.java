import java.util.Scanner;

/**
 * 除自身以外数组的乘积（LeetCode 238）
 *
 * 时间复杂度：O(n)
 * - 需要遍历数组两次
 * - 第一次遍历计算前缀积：O(n)
 * - 第二次遍历计算后缀积并更新结果：O(n)
 * - 总时间复杂度：O(n) + O(n) = O(n)
 *
 * 空间复杂度：O(1)
 * - 不考虑输出数组，只使用了常数级别的额外空间
 * - 后缀积使用一个变量存储，空间复杂度为O(1)
 */
public class ProductExceptSelf238 {
    public static void main(String[] args) {
        // 创建 Scanner 对象用于读取用户输入
        Scanner scanner = new Scanner(System.in);

        // 读取输入的数组
        System.out.print("请输入数组元素，以空格分隔：");

        // 读取一行输入
        String line = scanner.nextLine();

        // 按空格分割字符串得到字符串数组
        String[] str = line.split(" ");

        // 获取数组长度
        int n = str.length;

        // 创建整型数组
        int[] nums = new int[n];

        // 将字符串数组转换为整型数组
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(str[i]);
        }

        // 调用 productExceptSelf 方法计算结果
        int[] result = productExceptSelf(nums);

        // 输出结果
        System.out.print("结果数组为：");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println(); // 换行
    }

    /**
     * 计算除自身以外数组的乘积
     * 对于数组中每个元素，计算除该元素外其余所有元素的乘积
     *
     * 算法思路：
     * 1. 对于每个位置 i，结果等于该位置左边所有元素的乘积乘以右边所有元素的乘积
     * 2. 分两步计算：
     *    - 第一步：计算每个位置的前缀积（左边所有元素的乘积）
     *    - 第二步：从右向左遍历，计算后缀积并乘以前缀积得到最终结果
     *
     * 示例过程（以数组 [1,2,3,4] 为例）：
     * 数组:      [1,  2,  3,  4]
     * 索引:       0   1   2   3
     *
     * 步骤1 - 计算前缀积:
     * answer[0] = 1 (左边无元素)
     * answer[1] = 1 * 1 = 1 (左边元素: 1)
     * answer[2] = 1 * 2 = 2 (左边元素: 1,2)
     * answer[3] = 2 * 3 = 6 (左边元素: 1,2,3)
     * 前缀积数组: [1, 1, 2, 6]
     *
     * 步骤2 - 计算后缀积并更新结果:
     * i=3: answer[3] = 6 * 1 = 6, suffixProduct = 1 * 4 = 4
     * i=2: answer[2] = 2 * 4 = 8, suffixProduct = 4 * 3 = 12
     * i=1: answer[1] = 1 * 12 = 12, suffixProduct = 12 * 2 = 24
     * i=0: answer[0] = 1 * 24 = 24, suffixProduct = 24 * 1 = 24
     *
     * 最终结果: [24, 12, 8, 6]
     * 验证: [2*3*4, 1*3*4, 1*2*4, 1*2*3] = [24, 12, 8, 6]
     *
     * 时间复杂度分析：
     * - 计算前缀积：O(n)，其中n为输入数组`nums`的长度
     * - 计算后缀积并更新结果：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 结果数组：O(n)
     * - 后缀积变量：O(1)
     * - 总空间复杂度：O(1)（不考虑输出数组）
     *
     * @param nums 输入的整数数组
     * @return 除自身以外数组的乘积数组
     */
    public static int[] productExceptSelf(int[] nums) {
        // 获取数组长度
        int n = nums.length;

        // 创建结果数组
        int[] answer = new int[n];

        // 1. 计算前缀积
        answer[0] = 1; // 第一个元素的左边没有元素，前缀积为 1
        // 从第二个元素开始，计算每个位置的前缀积
        for (int i = 1; i < n; i++) {
            // 当前元素的前缀积等于前一个位置的前缀积乘以前一个元素
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // 2. 计算后缀积并更新结果
        int suffixProduct = 1; // 初始化后缀积为1
        // 从右向左遍历数组
        for (int i = n - 1; i >= 0; i--) {
            // 当前结果等于前缀积乘以后缀积
            answer[i] = answer[i] * suffixProduct;
            // 更新后缀积（为下一个位置准备）
            suffixProduct *= nums[i];
        }

        // 返回结果数组
        return answer;
    }


    /**
     * 方法2：使用两个数组分别存储前缀积和后缀积
     *
     * 算法思路：
     * 1. 创建前缀积数组和后缀积数组
     * 2. 分别计算前缀积和后缀积
     * 3. 结果为对应位置前缀积与后缀积的乘积
     *
     * 示例过程（以数组 [1,2,3,4] 为例）：
     *
     * 1. 计算前缀积数组:
     *    prefix[0] = 1
     *    prefix[1] = 1 * 1 = 1
     *    prefix[2] = 1 * 2 = 2
     *    prefix[3] = 2 * 3 = 6
     *    prefix = [1, 1, 2, 6]
     *
     * 2. 计算后缀积数组:
     *    suffix[3] = 1
     *    suffix[2] = 1 * 4 = 4
     *    suffix[1] = 4 * 3 = 12
     *    suffix[0] = 12 * 2 = 24
     *    suffix = [24, 12, 4, 1]
     *
     * 3. 计算结果数组:
     *    answer[0] = prefix[0] * suffix[0] = 1 * 24 = 24
     *    answer[1] = prefix[1] * suffix[1] = 1 * 12 = 12
     *    answer[2] = prefix[2] * suffix[2] = 2 * 4 = 8
     *    answer[3] = prefix[3] * suffix[3] = 6 * 1 = 6
     *    answer = [24, 12, 8, 6]
     *
     * 时间复杂度分析：
     * - 计算前缀积数组：O(n)，其中n为输入数组`nums`的长度
     * - 计算后缀积数组：O(n)
     * - 计算结果数组：O(n)
     * - 总时间复杂度：O(n)
     *
     * 空间复杂度分析：
     * - 前缀积数组：O(n)
     * - 后缀积数组：O(n)
     * - 结果数组：O(n)
     * - 总空间复杂度：O(n)
     *
     * @param nums 输入的整数数组
     * @return 除自身以外数组的乘积数组
     */
    public static int[] productExceptSelfTwoArrays(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] suffix = new int[n];
        int[] answer = new int[n];

        // 计算前缀积数组
        prefix[0] = 1;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }

        // 计算后缀积数组
        suffix[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }

        // 计算结果数组
        for (int i = 0; i < n; i++) {
            answer[i] = prefix[i] * suffix[i];
        }

        return answer;
    }

    /**
     * 方法3：暴力解法（仅供学习对比，效率较低）
     *
     * 算法思路：
     * 对于每个元素，遍历整个数组计算除该元素外所有元素的乘积
     *
     * 示例过程（以数组 [1,2,3,4] 为例）：
     *
     * i=0: product = 1
     *      j=1: product = 1 * 2 = 2
     *      j=2: product = 2 * 3 = 6
     *      j=3: product = 6 * 4 = 24
     *      answer[0] = 24
     *
     * i=1: product = 1
     *      j=0: product = 1 * 1 = 1
     *      j=2: product = 1 * 3 = 3
     *      j=3: product = 3 * 4 = 12
     *      answer[1] = 12
     *
     * i=2: product = 1
     *      j=0: product = 1 * 1 = 1
     *      j=1: product = 1 * 2 = 2
     *      j=3: product = 2 * 4 = 8
     *      answer[2] = 8
     *
     * i=3: product = 1
     *      j=0: product = 1 * 1 = 1
     *      j=1: product = 1 * 2 = 2
     *      j=2: product = 2 * 3 = 6
     *      answer[3] = 6
     *
     * 最终结果: [24, 12, 8, 6]
     *
     * 时间复杂度分析：
     * - 外层循环：O(n)，其中n为输入数组`nums`的长度
     * - 内层循环：O(n)
     * - 总时间复杂度：O(n²)
     *
     * 空间复杂度分析：
     * - 结果数组：O(n)
     * - 临时变量product：O(1)
     * - 总空间复杂度：O(1)（不考虑输出数组）
     *
     * @param nums 输入的整数数组
     * @return 除自身以外数组的乘积数组
     */
    public static int[] productExceptSelfBruteForce(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            int product = 1;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    product *= nums[j];
                }
            }
            answer[i] = product;
        }

        return answer;
    }
}
