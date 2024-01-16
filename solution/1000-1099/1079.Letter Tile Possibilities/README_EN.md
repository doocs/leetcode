# [1079. Letter Tile Possibilities](https://leetcode.com/problems/letter-tile-possibilities)

[中文文档](/solution/1000-1099/1079.Letter%20Tile%20Possibilities/README.md)

## Description

<p>You have <code>n</code>&nbsp;&nbsp;<code>tiles</code>, where each tile has one letter <code>tiles[i]</code> printed on it.</p>

<p>Return <em>the number of possible non-empty sequences of letters</em> you can make using the letters printed on those <code>tiles</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tiles = &quot;AAB&quot;
<strong>Output:</strong> 8
<strong>Explanation: </strong>The possible sequences are &quot;A&quot;, &quot;B&quot;, &quot;AA&quot;, &quot;AB&quot;, &quot;BA&quot;, &quot;AAB&quot;, &quot;ABA&quot;, &quot;BAA&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tiles = &quot;AAABBC&quot;
<strong>Output:</strong> 188
</pre>

<p><strong class="example">Example 3:</strong></p>

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

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def numTilePossibilities(self, tiles: str) -> int:
        def dfs(cnt: Counter) -> int:
            ans = 0
            for i, x in cnt.items():
                if x > 0:
                    ans += 1
                    cnt[i] -= 1
                    ans += dfs(cnt)
                    cnt[i] += 1
            return ans

        cnt = Counter(tiles)
        return dfs(cnt)
```

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

```cpp
class Solution {
public:
    int numTilePossibilities(string tiles) {
        int cnt[26]{};
        for (char c : tiles) {
            ++cnt[c - 'A'];
        }
        function<int(int* cnt)> dfs = [&](int* cnt) -> int {
            int res = 0;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > 0) {
                    ++res;
                    --cnt[i];
                    res += dfs(cnt);
                    ++cnt[i];
                }
            }
            return res;
        };
        return dfs(cnt);
    }
};
```

```go
func numTilePossibilities(tiles string) int {
	cnt := [26]int{}
	for _, c := range tiles {
		cnt[c-'A']++
	}
	var dfs func(cnt [26]int) int
	dfs = func(cnt [26]int) (res int) {
		for i, x := range cnt {
			if x > 0 {
				res++
				cnt[i]--
				res += dfs(cnt)
				cnt[i]++
			}
		}
		return
	}
	return dfs(cnt)
}
```

```ts
function numTilePossibilities(tiles: string): number {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of tiles) {
        ++cnt[c.charCodeAt(0) - 'A'.charCodeAt(0)];
    }
    const dfs = (cnt: number[]): number => {
        let res = 0;
        for (let i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                ++res;
                --cnt[i];
                res += dfs(cnt);
                ++cnt[i];
            }
        }
        return res;
    };
    return dfs(cnt);
}
```

<!-- tabs:end -->

<!-- end -->
