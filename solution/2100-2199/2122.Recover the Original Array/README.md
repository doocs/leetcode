# [2122. 还原原数组](https://leetcode.cn/problems/recover-the-original-array)

[English Version](/solution/2100-2199/2122.Recover%20the%20Original%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 有一个下标从 <strong>0</strong> 开始的数组 <code>arr</code> ，由 <code>n</code> 个正整数组成。她会选择一个任意的 <strong>正整数 </strong><code>k</code> 并按下述方式创建两个下标从 <strong>0</strong> 开始的新整数数组 <code>lower</code> 和 <code>higher</code> ：</p>

<ol>
	<li>对每个满足 <code>0 &lt;= i &lt; n</code> 的下标 <code>i</code> ，<code>lower[i] = arr[i] - k</code></li>
	<li>对每个满足 <code>0 &lt;= i &lt; n</code> 的下标 <code>i</code> ，<code>higher[i] = arr[i] + k</code></li>
</ol>

<p>不幸地是，Alice 丢失了全部三个数组。但是，她记住了在数组 <code>lower</code> 和 <code>higher</code> 中出现的整数，但不知道每个整数属于哪个数组。请你帮助 Alice 还原原数组。</p>

<p>给你一个由 2n 个整数组成的整数数组 <code>nums</code> ，其中 <strong>恰好</strong> <code>n</code> 个整数出现在 <code>lower</code> ，剩下的出现在 <code>higher</code> ，还原并返回 <strong>原数组</strong> <code>arr</code> 。如果出现答案不唯一的情况，返回 <strong>任一</strong> 有效数组。</p>

<p><strong>注意：</strong>生成的测试用例保证存在 <strong>至少一个</strong> 有效数组 <code>arr</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,10,6,4,8,12]
<strong>输出：</strong>[3,7,11]
<strong>解释：</strong>
如果 arr = [3,7,11] 且 k = 1 ，那么 lower = [2,6,10] 且 higher = [4,8,12] 。
组合 lower 和 higher 得到 [2,6,10,4,8,12] ，这是 nums 的一个排列。
另一个有效的数组是 arr = [5,7,9] 且 k = 3 。在这种情况下，lower = [2,4,6] 且 higher = [8,10,12] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,3,3]
<strong>输出：</strong>[2,2]
<strong>解释：</strong>
如果 arr = [2,2] 且 k = 1 ，那么 lower = [1,1] 且 higher = [3,3] 。
组合 lower 和 higher 得到 [1,1,3,3] ，这是 nums 的一个排列。
注意，数组不能是 [1,3] ，因为在这种情况下，获得 [1,1,3,3] 唯一可行的方案是 k = 0 。
这种方案是无效的，k 必须是一个正整数。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [5,435]
<strong>输出：</strong>[220]
<strong>解释：</strong>
唯一可行的组合是 arr = [220] 且 k = 215 。在这种情况下，lower = [5] 且 higher = [435] 。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 * n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>生成的测试用例保证存在 <strong>至少一个</strong> 有效数组 <code>arr</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

对 nums 排序后，`nums[0]` 必然是 `lower[0]`，接下来从在 `[1, i)` 区间内枚举 `higher[0]`，然后使用双指针遍历 nums，得到剩余的 lower 和 higher 元素。

双指针遍历时，可以用 vis 数组标记 higher 中出现过的数字。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def recoverArray(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        for i in range(1, n):
            d = nums[i] - nums[0]
            if d == 0 or d % 2 == 1:
                continue
            vis = [False] * n
            vis[i] = True
            ans = [(nums[0] + nums[i]) >> 1]
            l, r = 1, i + 1
            while r < n:
                while l < n and vis[l]:
                    l += 1
                while r < n and nums[r] - nums[l] < d:
                    r += 1
                if r == n or nums[r] - nums[l] > d:
                    break
                vis[r] = True
                ans.append((nums[l] + nums[r]) >> 1)
                l, r = l + 1, r + 1
            if len(ans) == (n >> 1):
                return ans
        return []
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1, n = nums.length; i < n; ++i) {
            int d = nums[i] - nums[0];
            if (d == 0 || d % 2 == 1) {
                continue;
            }
            boolean[] vis = new boolean[n];
            vis[i] = true;
            List<Integer> t = new ArrayList<>();
            t.add((nums[0] + nums[i]) >> 1);
            for (int l = 1, r = i + 1; r < n; ++l, ++r) {
                while (l < n && vis[l]) {
                    ++l;
                }
                while (r < n && nums[r] - nums[l] < d) {
                    ++r;
                }
                if (r == n || nums[r] - nums[l] > d) {
                    break;
                }
                vis[r] = true;
                t.add((nums[l] + nums[r]) >> 1);
            }
            if (t.size() == (n >> 1)) {
                int[] ans = new int[t.size()];
                int idx = 0;
                for (int e : t) {
                    ans[idx++] = e;
                }
                return ans;
            }
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> recoverArray(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 1, n = nums.size(); i < n; ++i) {
            int d = nums[i] - nums[0];
            if (d == 0 || d % 2 == 1) continue;
            vector<bool> vis(n);
            vis[i] = true;
            vector<int> ans;
            ans.push_back((nums[0] + nums[i]) >> 1);
            for (int l = 1, r = i + 1; r < n; ++l, ++r) {
                while (l < n && vis[l]) ++l;
                while (r < n && nums[r] - nums[l] < d) ++r;
                if (r == n || nums[r] - nums[l] > d) break;
                vis[r] = true;
                ans.push_back((nums[l] + nums[r]) >> 1);
            }
            if (ans.size() == (n >> 1)) return ans;
        }
        return {};
    }
};
```

### **Go**

```go
func recoverArray(nums []int) []int {
	sort.Ints(nums)
	for i, n := 1, len(nums); i < n; i++ {
		d := nums[i] - nums[0]
		if d == 0 || d%2 == 1 {
			continue
		}
		vis := make([]bool, n)
		vis[i] = true
		ans := []int{(nums[0] + nums[i]) >> 1}
		for l, r := 1, i+1; r < n; l, r = l+1, r+1 {
			for l < n && vis[l] {
				l++
			}
			for r < n && nums[r]-nums[l] < d {
				r++
			}
			if r == n || nums[r]-nums[l] > d {
				break
			}
			vis[r] = true
			ans = append(ans, (nums[l]+nums[r])>>1)
		}
		if len(ans) == (n >> 1) {
			return ans
		}
	}
	return []int{}
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
