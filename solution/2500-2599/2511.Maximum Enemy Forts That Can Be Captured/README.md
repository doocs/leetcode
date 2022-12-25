# [2511. 最多可以摧毁的敌人城堡数目](https://leetcode.cn/problems/maximum-enemy-forts-that-can-be-captured)

[English Version](/solution/2500-2599/2511.Maximum%20Enemy%20Forts%20That%20Can%20Be%20Captured/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;，下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>forts</code>&nbsp;，表示一些城堡。<code>forts[i]</code> 可以是&nbsp;<code>-1</code>&nbsp;，<code>0</code>&nbsp;或者&nbsp;<code>1</code>&nbsp;，其中：</p>

<ul>
	<li><code>-1</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个位置 <strong>没有</strong>&nbsp;城堡。</li>
	<li><code>0</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个位置有一个 <strong>敌人</strong>&nbsp;的城堡。</li>
	<li><code>1</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个位置有一个你控制的城堡。</li>
</ul>

<p>现在，你需要决定，将你的军队从某个你控制的城堡位置&nbsp;<code>i</code>&nbsp;移动到一个空的位置&nbsp;<code>j</code>&nbsp;，满足：</p>

<ul>
	<li><code>0 &lt;= i, j &lt;= n - 1</code></li>
	<li>军队经过的位置 <strong>只有</strong>&nbsp;敌人的城堡。正式的，对于所有&nbsp;<code>min(i,j) &lt; k &lt; max(i,j)</code>&nbsp;的&nbsp;<code>k</code>&nbsp;，都满足&nbsp;<code>forts[k] == 0</code>&nbsp;。</li>
</ul>

<p>当军队移动时，所有途中经过的敌人城堡都会被 <strong>摧毁</strong> 。</p>

<p>请你返回 <strong>最多</strong>&nbsp;可以摧毁的敌人城堡数目。如果 <strong>无法</strong>&nbsp;移动你的军队，或者没有你控制的城堡，请返回 <code>0</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>forts = [1,0,0,-1,0,0,0,0,1]
<b>输出：</b>4
<strong>解释：</strong>
- 将军队从位置 0 移动到位置 3 ，摧毁 2 个敌人城堡，位置分别在 1 和 2 。
- 将军队从位置 8 移动到位置 3 ，摧毁 4 个敌人城堡。
4 是最多可以摧毁的敌人城堡数目，所以我们返回 4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>forts = [0,0,1,-1]
<b>输出：</b>0
<b>解释：</b>由于无法摧毁敌人的城堡，所以返回 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= forts.length &lt;= 1000</code></li>
	<li><code>-1 &lt;= forts[i] &lt;= 1</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：双指针**

我们用指针 $i$ 遍历数组 $forts$，指针 $j$ 从 $i$ 的下一个位置开始遍历，直到遇到第一个非 $0$ 的位置，即 $forts[j] \neq 0$。如果 $forts[i] + forts[j] = 0$，说明 $forts[i]$ 和 $forts[j]$ 是一对敌对城堡，我们可以将军队从 $forts[i]$ 移动到 $forts[j]$，摧毁 $j - i - 1$ 个敌人城堡。我们用变量 $ans$ 记录最多可以摧毁的敌人城堡数目即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `forts` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def captureForts(self, forts: List[int]) -> int:
        n = len(forts)
        i = ans = 0
        while i < n:
            j = i + 1
            if forts[i]:
                while j < n and forts[j] == 0:
                    j += 1
                if j < n and forts[i] + forts[j] == 0:
                    ans = max(ans, j - i - 1)
            i = j
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int captureForts(int[] forts) {
        int n = forts.length;
        int ans = 0, i = 0;
        while (i < n) {
            int j = i + 1;
            if (forts[i] != 0) {
                while (j < n && forts[j] == 0) {
                    ++j;
                }
                if (j < n && forts[i] + forts[j] == 0) {
                    ans = Math.max(ans, j - i - 1);
                }
            }
            i = j;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int captureForts(vector<int>& forts) {
        int n = forts.size();
        int ans = 0, i = 0;
        while (i < n) {
            int j = i + 1;
            if (forts[i] != 0) {
                while (j < n && forts[j] == 0) {
                    ++j;
                }
                if (j < n && forts[i] + forts[j] == 0) {
                    ans = max(ans, j - i - 1);
                }
            }
            i = j;
        }
        return ans;
    }
};
```

### **Go**

```go
func captureForts(forts []int) (ans int) {
	n := len(forts)
	i := 0
	for i < n {
		j := i + 1
		if forts[i] != 0 {
			for j < n && forts[j] == 0 {
				j++
			}
			if j < n && forts[i]+forts[j] == 0 {
				ans = max(ans, j-i-1)
			}
		}
		i = j
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function captureForts(forts: number[]): number {
    const n = forts.length;
    let ans = 0;
    let i = 0;
    while (i < n) {
        let j = i + 1;
        if (forts[i] !== 0) {
            while (j < n && forts[j] === 0) {
                j++;
            }
            if (j < n && forts[i] + forts[j] === 0) {
                ans = Math.max(ans, j - i - 1);
            }
        }
        i = j;
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn capture_forts(forts: Vec<i32>) -> i32 {
        let n = forts.len();
        let mut ans = 0;
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            if forts[i] != 0 {
                while j < n && forts[j] == 0 {
                    j += 1;
                }
                if j < n && forts[i] + forts[j] == 0 {
                    ans = ans.max(j - i - 1);
                }
            }
            i = j;
        }
        ans as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
