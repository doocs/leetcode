# [2834. 找出美丽数组的最小和](https://leetcode.cn/problems/find-the-minimum-possible-sum-of-a-beautiful-array)

[English Version](/solution/2800-2899/2834.Find%20the%20Minimum%20Possible%20Sum%20of%20a%20Beautiful%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个正整数：<code>n</code> 和 <code>target</code> 。</p>

<p>如果数组 <code>nums</code> 满足下述条件，则称其为 <strong>美丽数组</strong> 。</p>

<ul>
	<li><code>nums.length == n</code>.</li>
	<li><code>nums</code> 由两两互不相同的正整数组成。</li>
	<li>在范围 <code>[0, n-1]</code> 内，<strong>不存在 </strong>两个 <strong>不同</strong> 下标 <code>i</code> 和 <code>j</code> ，使得 <code>nums[i] + nums[j] == target</code> 。</li>
</ul>

<p>返回符合条件的美丽数组所可能具备的 <strong>最小</strong> 和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2, target = 3
<strong>输出：</strong>4
<strong>解释：</strong>nums = [1,3] 是美丽数组。
- nums 的长度为 n = 2 。
- nums 由两两互不相同的正整数组成。
- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
可以证明 4 是符合条件的美丽数组所可能具备的最小和。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3, target = 3
<strong>输出：</strong>8
<strong>解释：</strong>
nums = [1,3,4] 是美丽数组。 
- nums 的长度为 n = 3 。 
- nums 由两两互不相同的正整数组成。 
- 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
可以证明 8 是符合条件的美丽数组所可能具备的最小和。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1, target = 1
<strong>输出：</strong>1
<strong>解释：</strong>nums = [1] 是美丽数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 哈希表**

我们从正整数 $i=1$ 开始，依次判断 $i$ 是否可以加入数组中，如果可以加入，则将 $i$ 加入数组中，累加到答案中，然后将 $target-i$ 置为已访问，表示 $target-i$ 不能加入数组中。循环直到数组长度为 $n$。

时间复杂度 $O(n + target)$，空间复杂度 $O(n + target)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumPossibleSum(self, n: int, target: int) -> int:
        vis = set()
        ans = 0
        i = 1
        for _ in range(n):
            while i in vis:
                i += 1
            ans += i
            vis.add(target - i)
            i += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minimumPossibleSum(int n, int target) {
        boolean[] vis = new boolean[n + target];
        long ans = 0;
        for (int i = 1; n > 0; --n, ++i) {
            while (vis[i]) {
                ++i;
            }
            ans += i;
            if (target >= i) {
                vis[target - i] = true;
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
    long long minimumPossibleSum(int n, int target) {
        bool vis[n + target];
        memset(vis, false, sizeof(vis));
        long long ans = 0;
        for (int i = 1; n; ++i, --n) {
            while (vis[i]) {
                ++i;
            }
            ans += i;
            if (target >= i) {
                vis[target - i] = true;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumPossibleSum(n int, target int) (ans int64) {
	vis := make([]bool, n+target)
	for i := 1; n > 0; i, n = i+1, n-1 {
		for vis[i] {
			i++
		}
		ans += int64(i)
		if target >= i {
			vis[target-i] = true
		}
	}
	return
}
```

### **TypeScript**

```ts
function minimumPossibleSum(n: number, target: number): number {
    const vis: boolean[] = Array(n + target).fill(false);
    let ans = 0;
    for (let i = 1; n; ++i, --n) {
        while (vis[i]) {
            ++i;
        }
        ans += i;
        if (target >= i) {
            vis[target - i] = true;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
