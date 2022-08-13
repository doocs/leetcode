# [LCP 28. 采购方案](https://leetcode.cn/problems/4xy4Wx)

## 题目描述

<!-- 这里写题目描述 -->

小力将 N 个零件的报价存于数组 `nums`。小力预算为 `target`，假定小力仅购买两个零件，要求购买零件的花费不超过预算，请问他有多少种采购方案。

注意：答案需要以 `1e9 + 7 (1000000007)` 为底取模，如：计算初始结果为：`1000000008`，请返回 `1`

**示例 1：**

> 输入：`nums = [2,5,3,5], target = 6`
>
> 输出：`1`
>
> 解释：预算内仅能购买 nums[0] 与 nums[2]。

**示例 2：**

> 输入：`nums = [2,2,1,9], target = 10`
>
> 输出：`4`
>
> 解释：符合预算的采购方案如下：
> nums[0] + nums[1] = 4
> nums[0] + nums[2] = 3
> nums[1] + nums[2] = 3
> nums[2] + nums[3] = 10

**提示：**

-   `2 <= nums.length <= 10^5`
-   `1 <= nums[i], target <= 10^5`

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def purchasePlans(self, nums: List[int], target: int) -> int:
        nums.sort()
        i, j = 0, len(nums) - 1
        res = 0
        while i < j:
            if nums[i] + nums[j] > target:
                j -= 1
            else:
                res += j - i
                i += 1
        return res % 1000000007
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int purchasePlans(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0, mod = 1000000007;
        for (int i = 0, j = nums.length - 1; i < j; ++i) {
            while (i < j && nums[i] + nums[j] > target) {
                --j;
            }
            if (i < j) {
                res = (res + j - i) % mod;
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int purchasePlans(vector<int>& nums, int target) {
        const int MOD = 1000000007;
        sort(nums.begin(), nums.end());
        int res = 0;
        for (int i = 0, j = nums.size() - 1; i < j; ++i) {
            while (i < j && nums[i] + nums[j] > target) --j;
            if (i < j) res = (res + j - i) % MOD;
        }
        return res % MOD;
    }
};
```

### **Go**

```go
func purchasePlans(nums []int, target int) int {
	sort.Ints(nums)
	res, mod := 0, 1000000007
	for i, j := 0, len(nums)-1; i < j; i++ {
		for i < j && nums[i]+nums[j] > target {
			j--
		}
		if i < j {
			res = (res + j - i) % mod
		}
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
