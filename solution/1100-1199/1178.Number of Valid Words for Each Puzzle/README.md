# [1178. 猜字谜](https://leetcode.cn/problems/number-of-valid-words-for-each-puzzle)

[English Version](/solution/1100-1199/1178.Number%20of%20Valid%20Words%20for%20Each%20Puzzle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>外国友人仿照中国字谜设计了一个英文版猜字谜小游戏，请你来猜猜看吧。</p>

<p>字谜的迷面 <code>puzzle</code> 按字符串形式给出，如果一个单词 <code>word</code> 符合下面两个条件，那么它就可以算作谜底：</p>

<ul>
	<li>单词 <code>word</code> 中包含谜面 <code>puzzle</code> 的第一个字母。</li>
	<li>单词 <code>word</code> 中的每一个字母都可以在谜面 <code>puzzle</code> 中找到。<br />
	例如，如果字谜的谜面是 "abcdefg"，那么可以作为谜底的单词有 "faced", "cabbage", 和 "baggage"；而 "beefed"（不含字母 "a"）以及 "based"（其中的 "s" 没有出现在谜面中）都不能作为谜底。</li>
</ul>

<p>返回一个答案数组 <code>answer</code>，数组中的每个元素 <code>answer[i]</code> 是在给出的单词列表 <code>words</code> 中可以作为字谜迷面 <code>puzzles[i]</code> 所对应的谜底的单词数目。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>
words = ["aaaa","asas","able","ability","actt","actor","access"], 
puzzles = ["aboveyz","abrodyz","abslute","absoryz","actresz","gaswxyz"]
<strong>输出：</strong>[1,1,3,2,4,0]
<strong>解释：</strong>
1 个单词可以作为 "aboveyz" 的谜底 : "aaaa" 
1 个单词可以作为 "abrodyz" 的谜底 : "aaaa"
3 个单词可以作为 "abslute" 的谜底 : "aaaa", "asas", "able"
2 个单词可以作为 "absoryz" 的谜底 : "aaaa", "asas"
4 个单词可以作为 "actresz" 的谜底 : "aaaa", "asas", "actt", "access"
没有单词可以作为 "gaswxyz" 的谜底，因为列表中的单词都不含字母 'g'。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= words.length <= 10^5</code></li>
	<li><code>4 <= words[i].length <= 50</code></li>
	<li><code>1 <= puzzles.length <= 10^4</code></li>
	<li><code>puzzles[i].length == 7</code></li>
	<li><code>words[i][j]</code>, <code>puzzles[i][j]</code> 都是小写英文字母。</li>
	<li>每个 <code>puzzles[i]</code> 所包含的字符都不重复。</li>
</ul>

## 解法

### 方法一：状态压缩 + 哈希表 + 子集枚举

根据题目描述，对于字谜数组 $puzzles$ 中的每一个字谜 $p$，我们需要统计有多少个单词 $w$ 包含了字谜 $p$ 的第一个字母，且 $w$ 的每一个字母都可以在 $p$ 中找到。

由于每个单词重复的字母只需要统计一次，因此，我们可以使用二进制状态压缩的方法，将每个单词 $w$ 转换成一个二进制数 $mask$，其中 $mask$ 的第 $i$ 位为 $1$，当且仅当字母 $i$ 在单词 $w$ 中出现过。我们用哈希表 $cnt$ 统计所有单词的状态压缩后的值出现的次数。

接下来，遍历字谜数组 $puzzles$，对于每一个字谜 $p$，我们注意到其长度固定为 $7$，因此我们只需要枚举 $p$ 的子集，如果该子集包含 $p$ 的第一个字母，那么我们查找其在哈希表中对应的值并累加到当前字谜的答案中。

遍历结束后，我们就可以得到字谜数组 $puzzles$ 中每个字谜对应的谜底数量，将其返回即可。

时间复杂度 $O(m \times |w| + n \times 2^{|p|})$，空间复杂度 $O(m)$。其中 $m$ 和 $n$ 分别为数组 $words$ 和 $puzzles$ 的长度，而 $|w|$ 和 $|p|$ 分别为数组 $words$ 中单词的最大长度和数组 $puzzles$ 中字谜的长度。

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
