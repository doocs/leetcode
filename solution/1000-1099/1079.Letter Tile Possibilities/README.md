# [1079. 活字印刷](https://leetcode.cn/problems/letter-tile-possibilities)

[English Version](/solution/1000-1099/1079.Letter%20Tile%20Possibilities/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一套活字字模&nbsp;<code>tiles</code>，其中每个字模上都刻有一个字母&nbsp;<code>tiles[i]</code>。返回你可以印出的非空字母序列的数目。</p>

<p><strong>注意：</strong>本题中，每个活字字模只能使用一次。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>"AAB"
<strong>输出：</strong>8
<strong>解释：</strong>可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>"AAABBC"
<strong>输出：</strong>188
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>"V"
<strong>输出：</strong>1</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= tiles.length &lt;= 7</code></li>
	<li><code>tiles</code> 由大写英文字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numTilePossibilities(self, tiles: str) -> int:
        def dfs():
            ans = 0
            for i in range(26):
                if cnt[i]:
                    ans += 1
                    cnt[i] -= 1
                    ans += dfs()
                    cnt[i] += 1
            return ans

        cnt = [0] * 26
        for t in tiles:
            cnt[ord(t) - ord('A')] += 1
        return dfs()
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numTilePossibilities(String tiles) {
        int[] cnt = new int[26];
        for (char c : tiles.toCharArray()) {
            ++cnt[c - 'A'];
        }
        return dfs(cnt);
    }

    private int dfs(int[] cnt) {
        int res = 0;
        for (int i = 0; i < cnt.length; ++i) {
            if (cnt[i] > 0) {
                ++res;
                --cnt[i];
                res += dfs(cnt);
                ++cnt[i];
            }
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numTilePossibilities(string tiles) {
        vector<int> cnt(26);
        for (char& c : tiles) ++cnt[c - 'A'];
        return dfs(cnt);
    }

    int dfs(vector<int>& cnt) {
        int res = 0;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) {
                --cnt[i];
                ++res;
                res += dfs(cnt);
                ++cnt[i];
            }
        }
        return res;
    }
};
```

### **Go**

```go
func numTilePossibilities(tiles string) int {
	cnt := make([]int, 26)
	for _, c := range tiles {
		cnt[c-'A']++
	}
	var dfs func() int
	dfs = func() int {
		res := 0
		for i := 0; i < 26; i++ {
			if cnt[i] > 0 {
				res++
				cnt[i]--
				res += dfs()
				cnt[i]++
			}
		}
		return res
	}
	return dfs()
}
```

### **...**

```

```

<!-- tabs:end -->
