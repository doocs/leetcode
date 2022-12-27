# [2515. 到目标字符串的最短距离](https://leetcode.cn/problems/shortest-distance-to-target-string-in-a-circular-array)

[English Version](/solution/2500-2599/2515.Shortest%20Distance%20to%20Target%20String%20in%20a%20Circular%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的 <strong>环形</strong> 字符串数组 <code>words</code> 和一个字符串 <code>target</code> 。<strong>环形数组</strong> 意味着数组首尾相连。</p>

<ul>
	<li>形式上， <code>words[i]</code> 的下一个元素是 <code>words[(i + 1) % n]</code> ，而 <code>words[i]</code> 的前一个元素是 <code>words[(i - 1 + n) % n]</code> ，其中 <code>n</code> 是 <code>words</code> 的长度。</li>
</ul>

<p>从 <code>startIndex</code> 开始，你一次可以用 <code>1</code> 步移动到下一个或者前一个单词。</p>

<p>返回到达目标字符串 <code>target</code> 所需的最短距离。如果 <code>words</code> 中不存在字符串 <code>target</code> ，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>words = ["hello","i","am","leetcode","hello"], target = "hello", startIndex = 1
<strong>输出：</strong>1
<strong>解释：</strong>从下标 1 开始，可以经由以下步骤到达 "hello" ：
- 向右移动 3 个单位，到达下标 4 。
- 向左移动 2 个单位，到达下标 4 。
- 向右移动 4 个单位，到达下标 0 。
- 向左移动 1 个单位，到达下标 0 。
到达 "hello" 的最短距离是 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>words = ["a","b","leetcode"], target = "leetcode", startIndex = 0
<strong>输出：</strong>1
<strong>解释：</strong>从下标 0 开始，可以经由以下步骤到达 "leetcode" ：
- 向右移动 2 个单位，到达下标 3 。
- 向左移动 1 个单位，到达下标 3 。
到达 "leetcode" 的最短距离是 1 。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>words = ["i","eat","leetcode"], target = "ate", startIndex = 0
<strong>输出：</strong>-1
<strong>解释：</strong>因为 words 中不存在字符串 "ate" ，所以返回 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> 和 <code>target</code> 仅由小写英文字母组成</li>
	<li><code>0 &lt;= startIndex &lt; words.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

遍历数组，找到与 target 相等的单词，计算其与 startIndex 的距离 $t$，则此时的最短距离为 $min(t, n - t)$，我们只需要不断更新最小值即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def closetTarget(self, words: List[str], target: str, startIndex: int) -> int:
        n = len(words)
        ans = n
        for i, w in enumerate(words):
            if w == target:
                t = abs(i - startIndex)
                ans = min(ans, t, n - t)
        return -1 if ans == n else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            if (w.equals(target)) {
                int t = Math.abs(i - startIndex);
                ans = Math.min(ans, Math.min(t, n - t));
            }
        }
        return ans == n ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int closetTarget(vector<string>& words, string target, int startIndex) {
        int n = words.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            auto w = words[i];
            if (w == target) {
                int t = abs(i - startIndex);
                ans = min(ans, min(t, n - t));
            }
        }
        return ans == n ? -1 : ans;
    }
};
```

### **Go**

```go
func closetTarget(words []string, target string, startIndex int) int {
	n := len(words)
	ans := n
	for i, w := range words {
		if w == target {
			t := abs(i - startIndex)
			ans = min(ans, min(t, n-t))
		}
	}
	if ans == n {
		return -1
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
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
function closetTarget(
    words: string[],
    target: string,
    startIndex: number,
): number {
    const n = words.length;
    for (let i = 0; i <= n >> 1; i++) {
        if (
            words[(startIndex - i + n) % n] === target ||
            words[(startIndex + i) % n] === target
        ) {
            return i;
        }
    }
    return -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn closet_target(words: Vec<String>, target: String, start_index: i32) -> i32 {
        let start_index = start_index as usize;
        let n = words.len();
        for i in 0..=n >> 1 {
            if words[(start_index - i + n) % n] == target || words[(start_index + i) % n] == target
            {
                return i as i32;
            }
        }
        -1
    }
}
```

### **C**

```c
int closetTarget(char **words, int wordsSize, char *target, int startIndex) {
    for (int i = 0; i <= wordsSize >> 1; i++) {
        if (strcmp(words[(startIndex - i + wordsSize) % wordsSize], target) == 0 ||
            strcmp(words[(startIndex + i) % wordsSize], target) == 0) {
            return i;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
