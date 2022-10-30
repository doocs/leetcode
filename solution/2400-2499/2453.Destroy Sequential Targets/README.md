# [2453. 摧毁一系列目标](https://leetcode.cn/problems/destroy-sequential-targets)

[English Version](/solution/2400-2499/2453.Destroy%20Sequential%20Targets/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的数组&nbsp;<code>nums</code>&nbsp;，它包含若干正整数，表示数轴上你需要摧毁的目标所在的位置。同时给你一个整数&nbsp;<code>space</code>&nbsp;。</p>

<p>你有一台机器可以摧毁目标。给机器 <strong>输入</strong>&nbsp;<code>nums[i]</code>&nbsp;，这台机器会摧毁所有位置在&nbsp;<code>nums[i] + c * space</code>&nbsp;的目标，其中&nbsp;<code>c</code>&nbsp;是任意非负整数。你想摧毁&nbsp;<code>nums</code>&nbsp;中 <strong>尽可能多</strong>&nbsp;的目标。</p>

<p>请你返回在摧毁数目最多的前提下，<code>nums[i]</code>&nbsp;的 <strong>最小值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [3,7,8,1,1,5], space = 2
<b>输出：</b>1
<b>解释：</b>如果我们输入 nums[3] ，我们可以摧毁位于 1,3,5,7,9,... 这些位置的目标。
这种情况下， 我们总共可以摧毁 5 个目标（除了 nums[2]）。
没有办法摧毁多于 5 个目标，所以我们返回 nums[3] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,3,5,2,4,6], space = 2
<b>输出：</b>1
<b>解释：</b>输入 nums[0] 或者 nums[3] 都会摧毁 3 个目标。
没有办法摧毁多于 3 个目标。
由于 nums[0] 是最小的可以摧毁 3 个目标的整数，所以我们返回 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [6,2,5], space = 100
<b>输出：</b>2
<b>解释：</b>无论我们输入哪个数字，都只能摧毁 1 个目标。输入的最小整数是 nums[1] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= space &lt;=&nbsp;10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：取模 + 枚举**

我们遍历数组 `nums`，用哈希表 `cnt` 统计每个数模 `space` 后的余数出现的次数。次数越多，意味着可以摧毁的目标越多。我们找到最多次数的组，取组中的最小值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def destroyTargets(self, nums: List[int], space: int) -> int:
        cnt = Counter(v % space for v in nums)
        ans = mx = 0
        for v in nums:
            t = cnt[v % space]
            if t > mx or (t == mx and v < ans):
                ans = v
                mx = t
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int destroyTargets(int[] nums, int space) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            v %= space;
            cnt.put(v, cnt.getOrDefault(v, 0) + 1);
        }
        int ans = 0, mx = 0;
        for (int v : nums) {
            int t = cnt.get(v % space);
            if (t > mx || (t == mx && v < ans)) {
                ans = v;
                mx = t;
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
    int destroyTargets(vector<int>& nums, int space) {
        unordered_map<int, int> cnt;
        for (int v : nums) ++cnt[v % space];
        int ans = 0, mx = 0;
        for (int v : nums) {
            int t = cnt[v % space];
            if (t > mx || (t == mx && v < ans)) {
                ans = v;
                mx = t;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func destroyTargets(nums []int, space int) int {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v%space]++
	}
	ans, mx := 0, 0
	for _, v := range nums {
		t := cnt[v%space]
		if t > mx || (t == mx && v < ans) {
			ans = v
			mx = t
		}
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
