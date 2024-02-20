# [1178. Number of Valid Words for Each Puzzle](https://leetcode.com/problems/number-of-valid-words-for-each-puzzle)

[中文文档](/solution/1100-1199/1178.Number%20of%20Valid%20Words%20for%20Each%20Puzzle/README.md)

<!-- tags:Bit Manipulation,Trie,Array,Hash Table,String -->

## Description

With respect to a given <code>puzzle</code> string, a <code>word</code> is <em>valid</em> if both the following conditions are satisfied:

<ul>
	<li><code>word</code> contains the first letter of <code>puzzle</code>.</li>
	<li>For each letter in <code>word</code>, that letter is in <code>puzzle</code>.
	<ul>
		<li>For example, if the puzzle is <code>&quot;abcdefg&quot;</code>, then valid words are <code>&quot;faced&quot;</code>, <code>&quot;cabbage&quot;</code>, and <code>&quot;baggage&quot;</code>, while</li>
		<li>invalid words are <code>&quot;beefed&quot;</code> (does not include <code>&#39;a&#39;</code>) and <code>&quot;based&quot;</code> (includes <code>&#39;s&#39;</code> which is not in the puzzle).</li>
	</ul>
	</li>
</ul>
Return <em>an array </em><code>answer</code><em>, where </em><code>answer[i]</code><em> is the number of words in the given word list </em><code>words</code><em> that is valid with respect to the puzzle </em><code>puzzles[i]</code>.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;aaaa&quot;,&quot;asas&quot;,&quot;able&quot;,&quot;ability&quot;,&quot;actt&quot;,&quot;actor&quot;,&quot;access&quot;], puzzles = [&quot;aboveyz&quot;,&quot;abrodyz&quot;,&quot;abslute&quot;,&quot;absoryz&quot;,&quot;actresz&quot;,&quot;gaswxyz&quot;]
<strong>Output:</strong> [1,1,3,2,4,0]
<strong>Explanation:</strong> 
1 valid word for &quot;aboveyz&quot; : &quot;aaaa&quot; 
1 valid word for &quot;abrodyz&quot; : &quot;aaaa&quot;
3 valid words for &quot;abslute&quot; : &quot;aaaa&quot;, &quot;asas&quot;, &quot;able&quot;
2 valid words for &quot;absoryz&quot; : &quot;aaaa&quot;, &quot;asas&quot;
4 valid words for &quot;actresz&quot; : &quot;aaaa&quot;, &quot;asas&quot;, &quot;actt&quot;, &quot;access&quot;
There are no valid words for &quot;gaswxyz&quot; cause none of the words in the list contains letter &#39;g&#39;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;apple&quot;,&quot;pleas&quot;,&quot;please&quot;], puzzles = [&quot;aelwxyz&quot;,&quot;aelpxyz&quot;,&quot;aelpsxy&quot;,&quot;saelpxy&quot;,&quot;xaelpsy&quot;]
<strong>Output:</strong> [0,1,3,2,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>4 &lt;= words[i].length &lt;= 50</code></li>
	<li><code>1 &lt;= puzzles.length &lt;= 10<sup>4</sup></code></li>
	<li><code>puzzles[i].length == 7</code></li>
	<li><code>words[i]</code> and <code>puzzles[i]</code> consist of lowercase English letters.</li>
	<li>Each <code>puzzles[i] </code>does not contain repeated characters.</li>
</ul>

## Solutions

### Solution 1: State Compression + Hash Table + Subset Enumeration

According to the problem description, for each puzzle $p$ in the puzzle array $puzzles$, we need to count how many words $w$ contain the first letter of the puzzle $p$, and every letter in $w$ can be found in $p$.

Since each repeated letter in a word only needs to be counted once, we can use the method of binary state compression to convert each word $w$ into a binary number $mask$, where the $i$th bit of $mask$ is $1$ if and only if the letter $i$ appears in the word $w$. We use a hash table $cnt$ to count the number of times each compressed state of all words appears.

Next, we traverse the puzzle array $puzzles$. For each puzzle $p$, we note that its length is fixed at $7$, so we only need to enumerate the subsets of $p$. If the subset contains the first letter of $p$, then we look up its corresponding value in the hash table and add it to the current puzzle's answer.

After the traversal, we can get the number of puzzle solutions corresponding to each puzzle in the puzzle array $puzzles$, and return it.

The time complexity is $O(m \times |w| + n \times 2^{|p|})$, and the space complexity is $O(m)$. Here, $m$ and $n$ are the lengths of the arrays $words$ and $puzzles$ respectively, and $|w|$ and $|p|$ are the maximum length of the words in the array $words$ and the length of the puzzles in the array $puzzles$, respectively.

<!-- tabs:start -->

```python
class Solution:
    def findNumOfValidWords(self, words: List[str], puzzles: List[str]) -> List[int]:
        cnt = Counter()
        for w in words:
            mask = 0
            for c in w:
                mask |= 1 << (ord(c) - ord("a"))
            cnt[mask] += 1

        ans = []
        for p in puzzles:
            mask = 0
            for c in p:
                mask |= 1 << (ord(c) - ord("a"))
            x, i, j = 0, ord(p[0]) - ord("a"), mask
            while j:
                if j >> i & 1:
                    x += cnt[j]
                j = (j - 1) & mask
            ans.append(x)
        return ans
```

```java
class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        Map<Integer, Integer> cnt = new HashMap<>(words.length);
        for (var w : words) {
            int mask = 0;
            for (int i = 0; i < w.length(); ++i) {
                mask |= 1 << (w.charAt(i) - 'a');
            }
            cnt.merge(mask, 1, Integer::sum);
        }
        List<Integer> ans = new ArrayList<>();
        for (var p : puzzles) {
            int mask = 0;
            for (int i = 0; i < p.length(); ++i) {
                mask |= 1 << (p.charAt(i) - 'a');
            }
            int x = 0;
            int i = p.charAt(0) - 'a';
            for (int j = mask; j > 0; j = (j - 1) & mask) {
                if ((j >> i & 1) == 1) {
                    x += cnt.getOrDefault(j, 0);
                }
            }
            ans.add(x);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> findNumOfValidWords(vector<string>& words, vector<string>& puzzles) {
        unordered_map<int, int> cnt;
        for (auto& w : words) {
            int mask = 0;
            for (char& c : w) {
                mask |= 1 << (c - 'a');
            }
            cnt[mask]++;
        }
        vector<int> ans;
        for (auto& p : puzzles) {
            int mask = 0;
            for (char& c : p) {
                mask |= 1 << (c - 'a');
            }
            int x = 0;
            int i = p[0] - 'a';
            for (int j = mask; j; j = (j - 1) & mask) {
                if (j >> i & 1) {
                    x += cnt[j];
                }
            }
            ans.push_back(x);
        }
        return ans;
    }
};
```

```go
func findNumOfValidWords(words []string, puzzles []string) (ans []int) {
	cnt := map[int]int{}
	for _, w := range words {
		mask := 0
		for _, c := range w {
			mask |= 1 << (c - 'a')
		}
		cnt[mask]++
	}
	for _, p := range puzzles {
		mask := 0
		for _, c := range p {
			mask |= 1 << (c - 'a')
		}
		x, i := 0, p[0]-'a'
		for j := mask; j > 0; j = (j - 1) & mask {
			if j>>i&1 > 0 {
				x += cnt[j]
			}
		}
		ans = append(ans, x)
	}
	return
}
```

```ts
function findNumOfValidWords(words: string[], puzzles: string[]): number[] {
    const cnt: Map<number, number> = new Map();
    for (const w of words) {
        let mask = 0;
        for (const c of w) {
            mask |= 1 << (c.charCodeAt(0) - 97);
        }
        cnt.set(mask, (cnt.get(mask) || 0) + 1);
    }
    const ans: number[] = [];
    for (const p of puzzles) {
        let mask = 0;
        for (const c of p) {
            mask |= 1 << (c.charCodeAt(0) - 97);
        }
        let x = 0;
        const i = p.charCodeAt(0) - 97;
        for (let j = mask; j; j = (j - 1) & mask) {
            if ((j >> i) & 1) {
                x += cnt.get(j) || 0;
            }
        }
        ans.push(x);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
