# [2423. Remove Letter To Equalize Frequency](https://leetcode.com/problems/remove-letter-to-equalize-frequency)

[中文文档](/solution/2400-2499/2423.Remove%20Letter%20To%20Equalize%20Frequency/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>word</code>, consisting of lowercase English letters. You need to select <strong>one</strong> index and <strong>remove</strong> the letter at that index from <code>word</code> so that the <strong>frequency</strong> of every letter present in <code>word</code> is equal.</p>

<p>Return<em> </em><code>true</code><em> if it is possible to remove one letter so that the frequency of all letters in </em><code>word</code><em> are equal, and </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <b>frequency</b> of a letter <code>x</code> is the number of times it occurs in the string.</li>
	<li>You <strong>must</strong> remove exactly one letter and cannot chose to do nothing.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;abcc&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> Select index 3 and delete it: word becomes &quot;abc&quot; and each character has a frequency of 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word = &quot;aazz&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> We must delete a character, so either the frequency of &quot;a&quot; is 1 and the frequency of &quot;z&quot; is 2, or vice versa. It is impossible to make all present letters have equal frequency.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= word.length &lt;= 100</code></li>
	<li><code>word</code> consists of lowercase English letters only.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def equalFrequency(self, word: str) -> bool:
        for i in range(len(word)):
            cnt = Counter(word[:i] + word[i + 1:])
            if len(set(cnt.values())) == 1:
                return True
        return False
```

### **Java**

```java
class Solution {
    public boolean equalFrequency(String word) {
        for (int i = 0; i < word.length(); ++i) {
            int[] cnt = new int[26];
            for (int j = 0; j < word.length(); ++j) {
                if (j != i) {
                    ++cnt[word.charAt(j) - 'a'];
                }
            }
            Set<Integer> vis = new HashSet<>();
            for (int v : cnt) {
                if (v > 0) {
                    vis.add(v);
                }
            }
            if (vis.size() == 1) {
                return true;
            }
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool equalFrequency(string word) {
        for (int i = 0; i < word.size(); ++i) {
            int cnt[26] = {0};
            for (int j = 0; j < word.size(); ++j) {
                if (j != i) {
                    ++cnt[word[j] - 'a'];
                }
            }
            unordered_set<int> vis;
            for (int v : cnt) {
                if (v) {
                    vis.insert(v);
                }
            }
            if (vis.size() == 1) {
                return true;
            }
        }
        return false;
    }
};
```

### **Go**

```go
func equalFrequency(word string) bool {
	for i := range word {
		cnt := make([]int, 26)
		for j, c := range word {
			if j != i {
				cnt[c-'a']++
			}
		}
		vis := map[int]bool{}
		for _, v := range cnt {
			if v > 0 {
				vis[v] = true
			}
		}
		if len(vis) == 1 {
			return true
		}
	}
	return false
}
```

### **TypeScript**

```ts
function equalFrequency(word: string): boolean {
    const map = new Map();
    for (const c of word) {
        map.set(c, (map.get(c) ?? 0) + 1);
    }
    const count = new Map();
    for (const v of map.values()) {
        count.set(v, (count.get(v) ?? 0) + 1);
    }
    if (count.size === 1) {
        return map.size == 1 || [...count.keys()][0] === 1;
    }
    if (count.size === 2) {
        return [...count.entries()].some(
            (v, i, arr) =>
                (v[0] === 1 || v[0] - arr[i ^ 1][0] === 1) && v[1] === 1,
        );
    }
    return false;
}
```

### **...**

```

```

<!-- tabs:end -->
