# [2449. 使数组相似的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar)

[English Version](/solution/2400-2499/2449.Minimum%20Number%20of%20Operations%20to%20Make%20Arrays%20Similar/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数数组&nbsp;<code>nums</code> 和&nbsp;<code>target</code>&nbsp;，两个数组长度相等。</p>

<p>在一次操作中，你可以选择两个 <strong>不同</strong>&nbsp;的下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，其中&nbsp;<code>0 &lt;= i, j &lt; nums.length</code>&nbsp;，并且：</p>

<ul>
	<li>令&nbsp;<code>nums[i] = nums[i] + 2</code>&nbsp;且</li>
	<li>令&nbsp;<code>nums[j] = nums[j] - 2</code>&nbsp;。</li>
</ul>

<p>如果两个数组中每个元素出现的频率相等，我们称两个数组是 <strong>相似</strong>&nbsp;的。</p>

<p>请你返回将 <code>nums</code>&nbsp;变得与 <code>target</code>&nbsp;相似的最少操作次数。测试数据保证 <code>nums</code>&nbsp;一定能变得与 <code>target</code>&nbsp;相似。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [8,12,6], target = [2,14,10]
<b>输出：</b>2
<b>解释：</b>可以用两步操作将 nums 变得与 target 相似：
- 选择 i = 0 和 j = 2 ，nums = [10,12,4] 。
- 选择 i = 1 和 j = 2 ，nums = [10,14,2] 。
2 次操作是最少需要的操作次数。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,5], target = [4,1,3]
<b>输出：</b>1
<b>解释：</b>一步操作可以使 nums 变得与 target 相似：
- 选择 i = 1 和 j = 2 ，nums = [1,4,3] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1,1,1], target = [1,1,1,1,1]
<b>输出：</b>0
<b>解释：</b>数组 nums 已经与 target 相似。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length == target.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>nums</code>&nbsp;一定可以变得与&nbsp;<code>target</code> 相似。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：奇偶分类 + 排序**

注意到，由于每次操作，元素的值只会增加 $2$ 或减少 $2$，因此，元素的奇偶性不会改变。

因此，我们可以将数组 `nums` 和 `target` 分别按奇偶性分为两组，分别记为 $a_1$ 和 $a_2$，以及 $b_1$ 和 $b_2$。

那么，我们只需要将 $a_1$ 中的元素与 $b_1$ 中的元素配对，将 $a_2$ 中的元素与 $b_2$ 中的元素配对，然后进行操作。配对的过程中，我们可以使用贪心的策略，每次将 $a_i$ 中较小的元素与 $b_i$ 中较小的元素配对，这样可以保证操作的次数最少。这里可以直接通过排序来实现。

由于每次操作，都可以将对应位置的元素差值减少 $4$，因此，我们累计每个对应位置的差值，最后除以 $4$ 即可得到答案。

时间复杂度 $O(n\times \log n)$，其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def makeSimilar(self, nums: List[int], target: List[int]) -> int:
        nums.sort(key=lambda x: (x & 1, x))
        target.sort(key=lambda x: (x & 1, x))
        return sum(abs(a - b) for a, b in zip(nums, target)) // 4
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer> a1 = new ArrayList<>();
        List<Integer> a2 = new ArrayList<>();
        List<Integer> b1 = new ArrayList<>();
        List<Integer> b2 = new ArrayList<>();
        for (int v : nums) {
            if (v % 2 == 0) {
                a1.add(v);
            } else {
                a2.add(v);
            }
        }
        for (int v : target) {
            if (v % 2 == 0) {
                b1.add(v);
            } else {
                b2.add(v);
            }
        }
        long ans = 0;
        for (int i = 0; i < a1.size(); ++i) {
            ans += Math.abs(a1.get(i) - b1.get(i));
        }
        for (int i = 0; i < a2.size(); ++i) {
            ans += Math.abs(a2.get(i) - b2.get(i));
        }
        return ans / 4;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long makeSimilar(vector<int>& nums, vector<int>& target) {
        sort(nums.begin(), nums.end());
        sort(target.begin(), target.end());
        vector<int> a1;
        vector<int> a2;
        vector<int> b1;
        vector<int> b2;
        for (int v : nums) {
            if (v & 1)
                a1.emplace_back(v);
            else
                a2.emplace_back(v);
        }
        for (int v : target) {
            if (v & 1)
                b1.emplace_back(v);
            else
                b2.emplace_back(v);
        }
        long long ans = 0;
        for (int i = 0; i < a1.size(); ++i) ans += abs(a1[i] - b1[i]);
        for (int i = 0; i < a2.size(); ++i) ans += abs(a2[i] - b2[i]);
        return ans / 4;
    }
};
```

### **Go**

```go
func makeSimilar(nums []int, target []int) int64 {
	sort.Ints(nums)
	sort.Ints(target)
	a1, a2, b1, b2 := []int{}, []int{}, []int{}, []int{}
	for _, v := range nums {
		if v%2 == 0 {
			a1 = append(a1, v)
		} else {
			a2 = append(a2, v)
		}
	}
	for _, v := range target {
		if v%2 == 0 {
			b1 = append(b1, v)
		} else {
			b2 = append(b2, v)
		}
	}
	ans := 0
	for i := 0; i < len(a1); i++ {
		ans += abs(a1[i] - b1[i])
	}
	for i := 0; i < len(a2); i++ {
		ans += abs(a2[i] - b2[i])
	}
	return int64(ans / 4)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
