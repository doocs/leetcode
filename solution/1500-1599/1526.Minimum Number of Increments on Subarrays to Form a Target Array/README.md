# [1526. 形成目标数组的子数组最少增加次数](https://leetcode.cn/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array)

[English Version](/solution/1500-1599/1526.Minimum%20Number%20of%20Increments%20on%20Subarrays%20to%20Form%20a%20Target%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>target</code>&nbsp;和一个数组&nbsp;<code>initial</code>&nbsp;，<code>initial</code>&nbsp;数组与 <code>target</code>&nbsp; 数组有同样的维度，且一开始全部为 0 。</p>

<p>请你返回从 <code>initial</code>&nbsp;得到&nbsp; <code>target</code>&nbsp;的最少操作次数，每次操作需遵循以下规则：</p>

<ul>
	<li>在 <code>initial</code>&nbsp;中选择 <strong>任意</strong>&nbsp;子数组，并将子数组中每个元素增加 1 。</li>
</ul>

<p>答案保证在 32 位有符号整数以内。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>target = [1,2,3,2,1]
<strong>输出：</strong>3
<strong>解释：</strong>我们需要至少 3 次操作从 intial 数组得到 target 数组。
[0,0,0,0,0] 将下标为 0 到 4&nbsp;的元素（包含二者）加 1 。
[1,1,1,1,1] 将下标为 1 到 3 的元素（包含二者）加 1 。
[1,2,2,2,1] 将下表为 2 的元素增加 1 。
[1,2,3,2,1] 得到了目标数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>target = [3,1,1,2]
<strong>输出：</strong>4
<strong>解释：</strong>(initial)[0,0,0,0] -&gt; [1,1,1,1] -&gt; [1,1,1,2] -&gt; [2,1,1,2] -&gt; [3,1,1,2] (target) 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>target = [3,1,5,4,2]
<strong>输出：</strong>7
<strong>解释：</strong>(initial)[0,0,0,0,0] -&gt; [1,1,1,1,1] -&gt; [2,1,1,1,1] -&gt; [3,1,1,1,1] 
                                  -&gt; [3,1,2,2,2] -&gt; [3,1,3,3,2] -&gt; [3,1,4,4,2] -&gt; [3,1,5,4,2] (target)。
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>target = [1,1,1,1]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= target[i] &lt;= 10^5</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示得到 $target[0,..i]$ 的最少操作次数，初始时 $f[0] = target[0]$。

对于 $target[i]$，如果 $target[i] \leq target[i-1]$，则 $f[i] = f[i-1]$；否则 $f[i] = f[i-1] + target[i] - target[i-1]$。

最终答案即为 $f[n-1]$。

我们注意到 $f[i]$ 只与 $f[i-1]$ 有关，因此可以只用一个变量来维护操作次数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $target$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minNumberOperations(self, target: List[int]) -> int:
        return target[0] + sum(max(0, b - a) for a, b in pairwise(target))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minNumberOperations(int[] target) {
        int f = target[0];
        for (int i = 1; i < target.length; ++i) {
            if (target[i] > target[i - 1]) {
                f += target[i] - target[i - 1];
            }
        }
        return f;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minNumberOperations(vector<int>& target) {
        int f = target[0];
        for (int i = 1; i < target.size(); ++i) {
            if (target[i] > target[i - 1]) {
                f += target[i] - target[i - 1];
            }
        }
        return f;
    }
};
```

### **Go**

```go
func minNumberOperations(target []int) int {
	f := target[0]
	for i, x := range target[1:] {
		if x > target[i] {
			f += x - target[i]
		}
	}
	return f
}
```

### **TypeScript**

```ts
function minNumberOperations(target: number[]): number {
    let f = target[0];
    for (let i = 1; i < target.length; ++i) {
        if (target[i] > target[i - 1]) {
            f += target[i] - target[i - 1];
        }
    }
    return f;
}
```

### **...**

```

```

<!-- tabs:end -->
