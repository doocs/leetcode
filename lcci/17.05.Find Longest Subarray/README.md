# [面试题 17.05. 字母与数字](https://leetcode.cn/problems/find-longest-subarray-lcci)

[English Version](/lcci/17.05.Find%20Longest%20Subarray/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>给定一个放有字符和数字的数组，找到最长的子数组，且包含的字符和数字的个数相同。</p>

<p>返回该子数组，若存在多个最长子数组，返回左端点最小的。若不存在这样的数组，返回一个空数组。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;,&quot;H&quot;,&quot;I&quot;,&quot;J&quot;,&quot;K&quot;,&quot;L&quot;,&quot;M&quot;]

<strong>输出: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;]
</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入: </strong>[&quot;A&quot;,&quot;A&quot;]

<strong>输出: </strong>[]
</pre>

<p><strong>提示：</strong></p>

<ul>
	<li><code>array.length &lt;= 100000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和 + 哈希表**

题目要求找到最长的子数组，且包含的字符和数字的个数相同。我们可以将字符看作 $1$，数字看作 $-1$，那么问题就转化为：求最长的子数组，使得该子数组的和为 $0$。

我们可以运用前缀和的思想，用哈希表 $vis$ 记录每个前缀和第一次出现的位置，用变量 $mx$ 和 $k$ 分别记录最长的满足条件的子数组的长度和左端点位置。

接下来遍历数组，计算当前位置 $i$ 的前缀和 $s$：

-   如果当前位置的前缀和 $s$ 在哈希表 $vis$ 中存在，我们记第一次出现 $s$ 的位置为 $j$，那么区间 $[j + 1,..,i]$ 的子数组和就为 $0$。如果此前的最长子数组的长度小于当前子数组的长度，即 $mx \lt i - j$，我们就更新 $mx = i - j$ 和 $k = j + 1$。
-   否则，我们将当前位置的前缀和 $s$ 作为键，当前位置 $i$ 作为值，存入哈希表 $vis$ 中。

遍历结束后，返回左端点为 $k$，且长度为 $mx$ 的子数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findLongestSubarray(self, array: List[str]) -> List[str]:
        vis = {0: -1}
        s = mx = k = 0
        for i, x in enumerate(array):
            s += 1 if x.isalpha() else -1
            if s in vis:
                if mx < i - (j := vis[s]):
                    mx = i - j
                    k = j + 1
            else:
                vis[s] = i
        return array[k: k + mx]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int s = 0, mx = 0, k = 0;
        for (int i = 0; i < array.length; ++i) {
            s += array[i].charAt(0) >= 'A' ? 1 : -1;
            if (vis.containsKey(s)) {
                int j = vis.get(s);
                if (mx < i - j) {
                    mx = i - j;
                    k = j + 1;
                }
            } else {
                vis.put(s, i);
            }
        }
        String[] ans = new String[mx];
        System.arraycopy(array, k, ans, 0, mx);
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findLongestSubarray(vector<string>& array) {
        unordered_map<int, int> vis{{0, -1}};
        int s = 0, mx = 0, k = 0;
        for (int i = 0; i < array.size(); ++i) {
            s += array[i][0] >= 'A' ? 1 : -1;
            if (vis.count(s)) {
                int j = vis[s];
                if (mx < i - j) {
                    mx = i - j;
                    k = j + 1;
                }
            } else {
                vis[s] = i;
            }
        }
        return vector<string>(array.begin() + k, array.begin() + k + mx);
    }
};
```

### **Go**

```go
func findLongestSubarray(array []string) []string {
	vis := map[int]int{0: -1}
	var s, mx, k int
	for i, x := range array {
		if x[0] >= 'A' {
			s++
		} else {
			s--
		}
		if j, ok := vis[s]; ok {
			if mx < i-j {
				mx = i - j
				k = j + 1
			}
		} else {
			vis[s] = i
		}
	}
	return array[k : k+mx]
}
```

### **TypeScript**

```ts
function findLongestSubarray(array: string[]): string[] {
    const vis = new Map();
    vis.set(0, -1);
    let s = 0,
        mx = 0,
        k = 0;
    for (let i = 0; i < array.length; ++i) {
        s += array[i] >= 'A' ? 1 : -1;
        if (vis.has(s)) {
            const j = vis.get(s);
            if (mx < i - j) {
                mx = i - j;
                k = j + 1;
            }
        } else {
            vis.set(s, i);
        }
    }
    return array.slice(k, k + mx);
}
```

### **...**

```

```

<!-- tabs:end -->
