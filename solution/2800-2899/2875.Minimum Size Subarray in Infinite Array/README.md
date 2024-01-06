# [2875. 无限数组的最短子数组](https://leetcode.cn/problems/minimum-size-subarray-in-infinite-array)

[English Version](/solution/2800-2899/2875.Minimum%20Size%20Subarray%20in%20Infinite%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的数组 <code>nums</code> 和一个整数 <code>target</code> 。</p>

<p>下标从 <strong>0</strong> 开始的数组 <code>infinite_nums</code> 是通过无限地将 nums 的元素追加到自己之后生成的。</p>

<p>请你从 <code>infinite_nums</code> 中找出满足 <strong>元素和</strong> 等于&nbsp;<code>target</code> 的 <strong>最短</strong> 子数组，并返回该子数组的长度。如果不存在满足条件的子数组，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3], target = 5
<strong>输出：</strong>2
<strong>解释：</strong>在这个例子中 infinite_nums = [1,2,3,1,2,3,1,2,...] 。
区间 [1,2] 内的子数组的元素和等于 target = 5 ，且长度 length = 2 。
可以证明，当元素和等于目标值 target = 5 时，2 是子数组的最短长度。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,2,3], target = 4
<strong>输出：</strong>2
<strong>解释：</strong>在这个例子中 infinite_nums = [1,1,1,2,3,1,1,1,2,3,1,1,...].
区间 [4,5] 内的子数组的元素和等于 target = 4 ，且长度 length = 2 。
可以证明，当元素和等于目标值 target = 4 时，2 是子数组的最短长度。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,4,6,8], target = 3
<strong>输出：</strong>-1
<strong>解释：</strong>在这个例子中 infinite_nums = [2,4,6,8,2,4,6,8,...] 。
可以证明，不存在元素和等于目标值 target = 3 的子数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 哈希表**

我们先算出数组 $nums$ 的元素总和，记为 $s$。

如果 $target \gt s$，那么我们可以将 $target$ 减去 $\lfloor \frac{target}{s} \rfloor \times s$，这样就可以将 $target$ 减小到 $[0, s)$ 的范围内。那么此时子数组的长度为 $a = \lfloor \frac{target}{s} \rfloor \times n$，其中 $n$ 是数组 $nums$ 的长度。

接下来，我们只需要在数组 $nums$ 中，找出长度最短的且元素和等于 $target$ 的子数组，或者长度最短的且前缀和加上后缀和等于 $target$，即子数组元素和等于 $s - target$ 的子数组，记长度为 $b$。我们可以通过前缀和加哈希表的方法，找出这样的子数组。

如果找到了这样的子数组，那么最终的答案就是 $a + b$。否则，答案就是 $-1$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minSizeSubarray(self, nums: List[int], target: int) -> int:
        s = sum(nums)
        n = len(nums)
        a = 0
        if target > s:
            a = n * (target // s)
            target -= target // s * s
        if target == s:
            return n
        pos = {0: -1}
        pre = 0
        b = inf
        for i, x in enumerate(nums):
            pre += x
            if (t := pre - target) in pos:
                b = min(b, i - pos[t])
            if (t := pre - (s - target)) in pos:
                b = min(b, n - (i - pos[t]))
            pos[pre] = i
        return -1 if b == inf else a + b
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minSizeSubarray(int[] nums, int target) {
        long s = Arrays.stream(nums).sum();
        int n = nums.length;
        int a = 0;
        if (target > s) {
            a = n * (target / (int) s);
            target -= target / s * s;
        }
        if (target == s) {
            return n;
        }
        Map<Long, Integer> pos = new HashMap<>();
        pos.put(0L, -1);
        long pre = 0;
        int b = 1 << 30;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            if (pos.containsKey(pre - target)) {
                b = Math.min(b, i - pos.get(pre - target));
            }
            if (pos.containsKey(pre - (s - target))) {
                b = Math.min(b, n - (i - pos.get(pre - (s - target))));
            }
            pos.put(pre, i);
        }
        return b == 1 << 30 ? -1 : a + b;
    }
}
```

```java
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        int minLength = n * 2 + 1;
        int l = 0;
        int sum = 0;

        for (int r = 0; r < n * 2; r++) {
            int start = l % n;
            int end = r % n;
            sum += nums[end];

            while (sum > k && l <= r) {
                start = l % n;
                sum -= nums[start];
                l++;
            }

            if (sum == k) {
                minLength = Math.min(minLength, r - l + 1);
                start = l % n;
                sum -= nums[start];
                l++;
            }
        }

        return minLength == n * 2 + 1 ? -1 : minLength;
    }
    public int minSizeSubarray(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }
        int k = target % sum;
        int ans = target / sum * n;
        if (k == 0) {
            return ans;
        }
        int res = shortestSubarray(nums, k);
        return res == -1 ? -1 : ans + res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minSizeSubarray(vector<int>& nums, int target) {
        long long s = accumulate(nums.begin(), nums.end(), 0LL);
        int n = nums.size();
        int a = 0;
        if (target > s) {
            a = n * (target / s);
            target -= target / s * s;
        }
        if (target == s) {
            return n;
        }
        unordered_map<int, int> pos{{0, -1}};
        long long pre = 0;
        int b = 1 << 30;
        for (int i = 0; i < n; ++i) {
            pre += nums[i];
            if (pos.count(pre - target)) {
                b = min(b, i - pos[pre - target]);
            }
            if (pos.count(pre - (s - target))) {
                b = min(b, n - (i - pos[pre - (s - target)]));
            }
            pos[pre] = i;
        }
        return b == 1 << 30 ? -1 : a + b;
    }
};
```

### **Go**

```go
func minSizeSubarray(nums []int, target int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	n := len(nums)
	a := 0
	if target > s {
		a = n * (target / s)
		target -= target / s * s
	}
	if target == s {
		return n
	}
	pos := map[int]int{0: -1}
	pre := 0
	b := 1 << 30
	for i, x := range nums {
		pre += x
		if j, ok := pos[pre-target]; ok {
			b = min(b, i-j)
		}
		if j, ok := pos[pre-(s-target)]; ok {
			b = min(b, n-(i-j))
		}
		pos[pre] = i
	}
	if b == 1<<30 {
		return -1
	}
	return a + b
}
```

### **TypeScript**

```ts
function minSizeSubarray(nums: number[], target: number): number {
    const s = nums.reduce((a, b) => a + b);
    const n = nums.length;
    let a = 0;
    if (target > s) {
        a = n * ((target / s) | 0);
        target -= ((target / s) | 0) * s;
    }
    if (target === s) {
        return n;
    }
    const pos: Map<number, number> = new Map();
    let pre = 0;
    pos.set(0, -1);
    let b = Infinity;
    for (let i = 0; i < n; ++i) {
        pre += nums[i];
        if (pos.has(pre - target)) {
            b = Math.min(b, i - pos.get(pre - target)!);
        }
        if (pos.has(pre - (s - target))) {
            b = Math.min(b, n - (i - pos.get(pre - (s - target))!));
        }
        pos.set(pre, i);
    }
    return b === Infinity ? -1 : a + b;
}
```

### **...**

```

```

<!-- tabs:end -->
