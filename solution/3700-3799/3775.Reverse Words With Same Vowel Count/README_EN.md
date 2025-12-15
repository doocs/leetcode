---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3775.Reverse%20Words%20With%20Same%20Vowel%20Count/README_EN.md
---

<!-- problem:start -->

# [3775. Reverse Words With Same Vowel Count](https://leetcode.com/problems/reverse-words-with-same-vowel-count)

[中文文档](/solution/3700-3799/3775.Reverse%20Words%20With%20Same%20Vowel%20Count/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>s</code> consisting of lowercase English words, each separated by a single space.</p>

<p>Determine how many vowels appear in the <strong>first</strong> word. Then, reverse each following word that has the <strong>same vowel count</strong>. Leave all remaining words unchanged.</p>

<p>Return the resulting string.</p>

<p>Vowels are <code>&#39;a&#39;</code>, <code>&#39;e&#39;</code>, <code>&#39;i&#39;</code>, <code>&#39;o&#39;</code>, and <code>&#39;u&#39;</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;cat and mice&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;cat dna mice&quot;</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>The first word <code>&quot;cat&quot;</code> has 1 vowel.</li>
	<li><code>&quot;and&quot;</code> has 1 vowel, so it is reversed to form <code>&quot;dna&quot;</code>.</li>
	<li><code>&quot;mice&quot;</code> has 2 vowels, so it remains unchanged.</li>
	<li>Thus, the resulting string is <code>&quot;cat dna mice&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;book is nice&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;book is ecin&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first word <code>&quot;book&quot;</code> has 2 vowels.</li>
	<li><code>&quot;is&quot;</code> has 1 vowel, so it remains unchanged.</li>
	<li><code>&quot;nice&quot;</code> has 2 vowels, so it is reversed to form <code>&quot;ecin&quot;</code>.</li>
	<li>Thus, the resulting string is <code>&quot;book is ecin&quot;</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;banana healthy&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;banana healthy&quot;</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first word <code>&quot;banana&quot;</code> has 3 vowels.</li>
	<li><code>&quot;healthy&quot;</code> has 2 vowels, so it remains unchanged.</li>
	<li>Thus, the resulting string is <code>&quot;banana healthy&quot;</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of lowercase English letters and spaces.</li>
	<li>Words in <code>s</code> are separated by a <strong>single</strong> space.</li>
	<li><code>s</code> does <strong>not</strong> contain leading or trailing spaces.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first split the string by spaces into a word list $\textit{words}$. Then we calculate the number of vowels $\textit{cnt}$ in the first word. Next, we iterate through each subsequent word, calculate its number of vowels, and if it equals $\textit{cnt}$, reverse the word. Finally, we rejoin the processed word list into a string and return it.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string $s$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def reverseWords(self, s: str) -> str:
        def calc(w: str) -> int:
            return sum(c in "aeiou" for c in w)

        words = s.split()
        cnt = calc(words[0])
        ans = [words[0]]
        for w in words[1:]:
            if calc(w) == cnt:
                ans.append(w[::-1])
            else:
                ans.append(w)
        return " ".join(ans)
```

#### Java

```java
class Solution {
    public String reverseWords(String s) {
        String[] words = s.split("\\s+");

        int cnt = calc(words[0]);
        List<String> ans = new ArrayList<>();
        ans.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            String w = words[i];
            if (calc(w) == cnt) {
                ans.add(new StringBuilder(w).reverse().toString());
            } else {
                ans.add(w);
            }
        }
        return String.join(" ", ans);
    }

    private int calc(String w) {
        int res = 0;
        for (char c : w.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                res++;
            }
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    string reverseWords(string s) {
        stringstream ss(s);
        string w;
        ss >> w;
        int cnt = calc(w);

        string ans = w;

        while (ss >> w) {
            ans.push_back(' ');
            if (calc(w) == cnt) {
                reverse(w.begin(), w.end());
            }
            ans += w;
        }

        return ans;
    }

private:
    int calc(const string& w) {
        return count_if(w.begin(), w.end(), [](char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
        });
    }
};
```

#### Go

```go
func reverseWords(s string) string {
	words := strings.Fields(s)

	calc := func(w string) int {
		cnt := 0
		for _, c := range w {
			switch c {
			case 'a', 'e', 'i', 'o', 'u':
				cnt++
			}
		}
		return cnt
	}

	cnt := calc(words[0])
	var ans []string
	ans = append(ans, words[0])

	for i := 1; i < len(words); i++ {
		w := words[i]
		if calc(w) == cnt {
			b := []rune(w)
			for l, r := 0, len(b)-1; l < r; l, r = l+1, r-1 {
				b[l], b[r] = b[r], b[l]
			}
			w = string(b)
		}
		ans = append(ans, w)
	}

	return strings.Join(ans, " ")
}
```

#### TypeScript

```ts
function reverseWords(s: string): string {
    const words = s.split(/\s+/);

    const calc = (w: string): number => {
        let cnt = 0;
        for (const c of w) {
            if ('aeiou'.includes(c)) cnt++;
        }
        return cnt;
    };

    const cnt = calc(words[0]);
    const ans: string[] = [words[0]];

    for (let i = 1; i < words.length; i++) {
        let w = words[i];
        if (calc(w) === cnt) {
            w = w.split('').reverse().join('');
        }
        ans.push(w);
    }

    return ans.join(' ');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
