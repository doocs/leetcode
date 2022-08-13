# [2195. 向数组中追加 K 个整数](https://leetcode.cn/problems/append-k-integers-with-minimal-sum)

[English Version](/solution/2100-2199/2195.Append%20K%20Integers%20With%20Minimal%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> 。请你向 <code>nums</code> 中追加 <code>k</code> 个 <strong>未</strong> 出现在 <code>nums</code> 中的、<strong>互不相同</strong> 的 <strong>正</strong> 整数，并使结果数组的元素和 <strong>最小</strong> 。</p>

<p>返回追加到 <code>nums</code> 中的 <code>k</code> 个整数之和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,4,25,10,25], k = 2
<strong>输出：</strong>5
<strong>解释：</strong>在该解法中，向数组中追加的两个互不相同且未出现的正整数是 2 和 3 。
nums 最终元素和为 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70 ，这是所有情况中的最小值。
所以追加到数组中的两个整数之和是 2 + 3 = 5 ，所以返回 5 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [5,6], k = 6
<strong>输出：</strong>25
<strong>解释：</strong>在该解法中，向数组中追加的两个互不相同且未出现的正整数是 1 、2 、3 、4 、7 和 8 。
nums 最终元素和为 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36 ，这是所有情况中的最小值。
所以追加到数组中的两个整数之和是 1 + 2 + 3 + 4 + 7 + 8 = 25 ，所以返回 25 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心 + 数学**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        nums.append(0)
        nums.append(2 * 10**9)
        nums.sort()
        ans = 0
        for a, b in pairwise(nums):
            n = min(k, b - a - 1)
            if n <= 0:
                continue
            k -= n
            ans += (a + 1 + a + n) * n // 2
            if k == 0:
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minimalKSum(int[] nums, int k) {
        int[] arr = new int[nums.length + 2];
        arr[arr.length - 1] = (int) 2e9;
        for (int i = 0; i < nums.length; ++i) {
            arr[i + 1] = nums[i];
        }
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 1; i < arr.length; ++i) {
            int a = arr[i - 1], b = arr[i];
            int n = Math.min(k, b - a - 1);
            if (n <= 0) {
                continue;
            }
            k -= n;
            ans += (long) (a + 1 + a + n) * n / 2;
            if (k == 0) {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimalKSum(vector<int>& nums, int k) {
        nums.push_back(0);
        nums.push_back(2e9);
        sort(nums.begin(), nums.end());
        long long ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            int a = nums[i - 1], b = nums[i];
            int n = min(k, b - a - 1);
            if (n <= 0) continue;
            k -= n;
            ans += 1ll * (a + 1 + a + n) * n / 2;
            if (k == 0) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimalKSum(nums []int, k int) int64 {
	nums = append(nums, 0, 2e9)
	sort.Ints(nums)
	ans := 0
	for i := 1; i < len(nums); i++ {
		a, b := nums[i-1], nums[i]
		n := min(k, b-a-1)
		if n <= 0 {
			continue
		}
		k -= n
		ans += (a + 1 + a + n) * n / 2
		if k == 0 {
			break
		}
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
