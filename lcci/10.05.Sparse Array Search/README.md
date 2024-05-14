---
comment: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/10.05.Sparse%20Array%20Search/README.md
---

# [面试题 10.05. 稀疏数组搜索](https://leetcode.cn/problems/sparse-array-search-lcci)

[中文文档](/lcci/10.05.Sparse%20Array%20Search/README.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>稀疏数组搜索。有个排好序的字符串数组，其中散布着一些空字符串，编写一种方法，找出给定字符串的位置。</p>

<p><strong>示例1:</strong></p>

<pre><strong> 输入</strong>: words = [&quot;at&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;ball&quot;, &quot;&quot;, &quot;&quot;, &quot;car&quot;, &quot;&quot;, &quot;&quot;,&quot;dad&quot;, &quot;&quot;, &quot;&quot;], s = &quot;ta&quot;
<strong> 输出</strong>：-1
<strong> 说明</strong>: 不存在返回-1。
</pre>

<p><strong>示例2:</strong></p>

<pre><strong> 输入</strong>：words = [&quot;at&quot;, &quot;&quot;, &quot;&quot;, &quot;&quot;, &quot;ball&quot;, &quot;&quot;, &quot;&quot;, &quot;car&quot;, &quot;&quot;, &quot;&quot;,&quot;dad&quot;, &quot;&quot;, &quot;&quot;], s = &quot;ball&quot;
<strong> 输出</strong>：4
</pre>

<p><strong>提示:</strong></p>

<ol>
	<li>words的长度在[1, 1000000]之间</li>
</ol>

## 解法

### 方法一：二分搜索

我们设计一个函数 $dfs(i, j)$，表示在数组 $nums[i, j]$ 中寻找目标字符串。如果找到了，返回目标字符串所在的下标，否则返回 $-1$。那么答案就是 $dfs(0, n-1)$。

函数 $dfs(i, j)$ 的实现如下：

1. 如果 $i > j$，返回 $-1$。
2. 否则，我们取中间位置 $mid = (i + j) / 2$，然后递归调用 $dfs(i, mid-1)$，如果返回值不为 $-1$，说明在左半部分找到了目标字符串，直接返回。否则，如果 $words[mid] = s$，说明找到了目前字符串，直接返回。否则，递归调用 $dfs(mid+1, j)$ 并返回。

时间复杂度最坏情况下为 $O(n \times m)$，空间复杂度最坏情况下为 $O(n)$。其中 $n$ 和 $m$ 分别是字符串数组的长度和字符串 $s$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def findString(self, words: List[str], s: str) -> int:
        def dfs(i: int, j: int) -> int:
            if i > j:
                return -1
            mid = (i + j) >> 1
            l = dfs(i, mid - 1)
            if l != -1:
                return l
            if words[mid] == s:
                return mid
            return dfs(mid + 1, j)

        return dfs(0, len(words) - 1)
```

```java
class Solution {
    public int findString(String[] words, String s) {
        return dfs(words, s, 0, words.length - 1);
    }

    private int dfs(String[] words, String s, int i, int j) {
        if (i > j) {
            return -1;
        }
        int mid = (i + j) >> 1;
        int l = dfs(words, s, i, mid - 1);
        if (l != -1) {
            return l;
        }
        if (words[mid].equals(s)) {
            return mid;
        }
        return dfs(words, s, mid + 1, j);
    }
}
```

```cpp
class Solution {
public:
    int findString(vector<string>& words, string s) {
        function<int(int, int)> dfs = [&](int i, int j) {
            if (i > j) {
                return -1;
            }
            int mid = (i + j) >> 1;
            int l = dfs(i, mid - 1);
            if (l != -1) {
                return l;
            }
            if (words[mid] == s) {
                return mid;
            }
            return dfs(mid + 1, j);
        };
        return dfs(0, words.size() - 1);
    }
};
```

```go
func findString(words []string, s string) int {
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return -1
		}
		mid := (i + j) >> 1
		if l := dfs(i, mid-1); l != -1 {
			return l
		}
		if words[mid] == s {
			return mid
		}
		return dfs(mid+1, j)
	}
	return dfs(0, len(words)-1)
}
```

```ts
function findString(words: string[], s: string): number {
    const dfs = (i: number, j: number): number => {
        if (i > j) {
            return -1;
        }
        const mid = (i + j) >> 1;
        const l = dfs(i, mid - 1);
        if (l !== -1) {
            return l;
        }
        if (words[mid] === s) {
            return mid;
        }
        return dfs(mid + 1, j);
    };
    return dfs(0, words.length - 1);
}
```

```swift
class Solution {
    func findString(_ words: [String], _ s: String) -> Int {
        return dfs(words, s, 0, words.count - 1)
    }

    private func dfs(_ words: [String], _ s: String, _ i: Int, _ j: Int) -> Int {
        if i > j {
            return -1
        }
        let mid = (i + j) >> 1
        let left = dfs(words, s, i, mid - 1)
        if left != -1 {
            return left
        }
        if words[mid] == s {
            return mid
        }
        return dfs(words, s, mid + 1, j)
    }
}
```

<!-- tabs:end -->

<!-- end -->
