# [2423. Remove Letter To Equalize Frequency](https://leetcode.com/problems/remove-letter-to-equalize-frequency)

[中文文档](/solution/2400-2499/2423.Remove%20Letter%20To%20Equalize%20Frequency/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>word</code>, consisting of lowercase English letters. You need to select <strong>one</strong> index and <strong>remove</strong> the letter at that index from <code>word</code> so that the <strong>frequency</strong> of every letter present in <code>word</code> is equal.</p>

<p>Return<em> </em><code>true</code><em> if it is possible to remove one letter so that the frequency of all letters in </em><code>word</code><em> are equal, and </em><code>false</code><em> otherwise</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>The <b>frequency</b> of a letter <code>x</code> is the number of times it occurs in the string.</li>
	<li>You <strong>must</strong> remove exactly one letter and cannot choose to do nothing.</li>
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

### Solution 1: Counting + Enumeration

First, we use a hash table or an array of length $26$ named $cnt$ to count the number of occurrences of each letter in the string.

Next, we enumerate the $26$ letters. If letter $c$ appears in the string, we decrement its count by one, then check whether the counts of the remaining letters are the same. If they are, return `true`. Otherwise, increment the count of $c$ by one and continue to enumerate the next letter.

If the enumeration ends, it means that it is impossible to make the counts of the remaining letters the same by deleting one letter, so return `false`.

The time complexity is $O(n + C^2)$, and the space complexity is $O(C)$. Here, $n$ is the length of the string $word$, and $C$ is the size of the character set. In this problem, $C = 26$.

<!-- tabs:start -->

```python
class Solution:
    def equalFrequency(self, word: str) -> bool:
        cnt = Counter(word)
        for c in cnt.keys():
            cnt[c] -= 1
            if len(set(v for v in cnt.values() if v)) == 1:
                return True
            cnt[c] += 1
        return False
```

```java
class Solution {
    public boolean equalFrequency(String word) {
        int[] cnt = new int[26];
        for (int i = 0; i < word.length(); ++i) {
            ++cnt[word.charAt(i) - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                --cnt[i];
                int x = 0;
                boolean ok = true;
                for (int v : cnt) {
                    if (v == 0) {
                        continue;
                    }
                    if (x > 0 && v != x) {
                        ok = false;
                        break;
                    }
                    x = v;
                }
                if (ok) {
                    return true;
                }
                ++cnt[i];
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool equalFrequency(string word) {
        int cnt[26]{};
        for (char& c : word) {
            ++cnt[c - 'a'];
        }
        for (int i = 0; i < 26; ++i) {
            if (cnt[i]) {
                --cnt[i];
                int x = 0;
                bool ok = true;
                for (int v : cnt) {
                    if (v == 0) {
                        continue;
                    }
                    if (x && v != x) {
                        ok = false;
                        break;
                    }
                    x = v;
                }
                if (ok) {
                    return true;
                }
                ++cnt[i];
            }
        }
        return false;
    }
};
```

```go
func equalFrequency(word string) bool {
	cnt := [26]int{}
	for _, c := range word {
		cnt[c-'a']++
	}
	for i := range cnt {
		if cnt[i] > 0 {
			cnt[i]--
			x := 0
			ok := true
			for _, v := range cnt {
				if v == 0 {
					continue
				}
				if x > 0 && v != x {
					ok = false
					break
				}
				x = v
			}
			if ok {
				return true
			}
			cnt[i]++
		}
	}
	return false
}
```

```ts
function equalFrequency(word: string): boolean {
    const cnt: number[] = new Array(26).fill(0);
    for (const c of word) {
        cnt[c.charCodeAt(0) - 97]++;
    }
    for (let i = 0; i < 26; ++i) {
        if (cnt[i]) {
            cnt[i]--;
            let x = 0;
            let ok = true;
            for (const v of cnt) {
                if (v === 0) {
                    continue;
                }
                if (x && v !== x) {
                    ok = false;
                    break;
                }
                x = v;
            }
            if (ok) {
                return true;
            }
            cnt[i]++;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
