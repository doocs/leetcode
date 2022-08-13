# [2261. 含最多 K 个可整除元素的子数组](https://leetcode.cn/problems/k-divisible-elements-subarrays)

[English Version](/solution/2200-2299/2261.K%20Divisible%20Elements%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和两个整数 <code>k</code> 和 <code>p</code> ，找出并返回满足要求的不同的子数组数，要求子数组中最多 <code>k</code> 个可被 <code>p</code> 整除的元素。</p>

<p>如果满足下述条件之一，则认为数组 <code>nums1</code> 和 <code>nums2</code> 是 <strong>不同</strong> 数组：</p>

<ul>
	<li>两数组长度 <strong>不同</strong> ，或者</li>
	<li>存在 <strong>至少 </strong>一个下标 <code>i</code> 满足 <code>nums1[i] != nums2[i]</code> 。</li>
</ul>

<p><strong>子数组</strong> 定义为：数组中的连续元素组成的一个 <strong>非空</strong> 序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [<em><strong>2</strong></em>,3,3,<em><strong>2</strong></em>,<em><strong>2</strong></em>], k = 2, p = 2
<strong>输出：</strong>11
<strong>解释：</strong>
位于下标 0、3 和 4 的元素都可以被 p = 2 整除。
共计 11 个不同子数组都满足最多含 k = 2 个可以被 2 整除的元素：
[2]、[2,3]、[2,3,3]、[2,3,3,2]、[3]、[3,3]、[3,3,2]、[3,3,2,2]、[3,2]、[3,2,2] 和 [2,2] 。
注意，尽管子数组 [2] 和 [3] 在 nums 中出现不止一次，但统计时只计数一次。
子数组 [2,3,3,2,2] 不满足条件，因为其中有 3 个元素可以被 2 整除。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4], k = 4, p = 1
<strong>输出：</strong>10
<strong>解释：</strong>
nums 中的所有元素都可以被 p = 1 整除。
此外，nums 中的每个子数组都满足最多 4 个元素可以被 1 整除。
因为所有子数组互不相同，因此满足所有限制条件的子数组总数为 10 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i], p &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你可以设计并实现时间复杂度为 <code>O(n<sup>2</sup>)</code> 的算法解决此问题吗？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        n = len(nums)
        s = set()
        for i in range(n):
            cnt = 0
            t = ""
            for j in range(i, n):
                if nums[j] % p == 0:
                    cnt += 1
                if cnt <= k:
                    t += str(nums[j]) + ","
                    s.add(t)
                else:
                    break
        return len(s)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        Set<String> s = new HashSet<>();
        for (int i = 0, n = nums.length; i < n; ++i) {
            int cnt = 0;
            String t = "";
            for (int j = i; j < n; ++j) {
                if (nums[j] % p == 0) {
                    ++cnt;
                }
                if (cnt > k) {
                    break;
                }
                t += nums[j] + ",";
                s.add(t);
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countDistinct(vector<int>& nums, int k, int p) {
        unordered_set<string> s;
        for (int i = 0, n = nums.size(); i < n; ++i) {
            int cnt = 0;
            string t = "";
            for (int j = i; j < n; ++j) {
                if (nums[j] % p == 0) ++cnt;
                if (cnt > k) break;
                t += to_string(nums[j]) + ",";
                s.insert(t);
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func countDistinct(nums []int, k int, p int) int {
	s := map[string]bool{}
	for i, n := 0, len(nums); i < n; i++ {
		cnt := 0
		t := ""
		for j := i; j < n; j++ {
			if nums[j]%p == 0 {
				cnt++
			}
			if cnt > k {
				break
			}
			t += string(nums[j]) + ","
			s[t] = true
		}
	}
	return len(s)
}
```

### **TypeScript**

```ts
function countDistinct(nums: number[], k: number, p: number): number {
    const n = nums.length;
    const numSet = new Set(nums);
    const verfiedSet = new Set<number>();
    for (let i of numSet) {
        if (i % p != 0) continue;
        verfiedSet.add(i);
    }
    let ans = new Set<string>();
    for (let i = 0; i < n; i++) {
        let sub = [];
        for (let j = i, cnt = 0; j < n; j++) {
            const num = nums[j];
            if (verfiedSet.has(num)) cnt++;
            if (cnt > k) break;
            sub.push(num);
            const str = sub.join(',');
            ans.add(str);
        }
    }
    return ans.size;
}
```

### **...**

```

```

<!-- tabs:end -->
