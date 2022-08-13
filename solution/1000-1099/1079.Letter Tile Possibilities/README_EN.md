# [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities)

[中文文档](/solution/1000-1099/1079.Letter%20Tile%20Possibilities/README.md)

## Description

<p>You have <code>n</code>&nbsp;&nbsp;<code>tiles</code>, where each tile has one letter <code>tiles[i]</code> printed on it.</p>

<p>Return <em>the number of possible non-empty sequences of letters</em> you can make using the letters printed on those <code>tiles</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> tiles = &quot;AAB&quot;
<strong>Output:</strong> 8
<strong>Explanation: </strong>The possible sequences are &quot;A&quot;, &quot;B&quot;, &quot;AA&quot;, &quot;AB&quot;, &quot;BA&quot;, &quot;AAB&quot;, &quot;ABA&quot;, &quot;BAA&quot;.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> tiles = &quot;AAABBC&quot;
<strong>Output:</strong> 188
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> tiles = &quot;V&quot;
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= tiles.length &lt;= 7</code></li>
	<li><code>tiles</code> consists of uppercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
