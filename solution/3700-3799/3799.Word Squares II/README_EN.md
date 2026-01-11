---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3799.Word%20Squares%20II/README_EN.md
rating: 1606
source: Weekly Contest 483 Q2
tags:
    - Array
    - String
    - Backtracking
    - Enumeration
    - Sorting
---

<!-- problem:start -->

# [3799. Word Squares II](https://leetcode.com/problems/word-squares-ii)

[中文文档](/solution/3700-3799/3799.Word%20Squares%20II/README.md)

## Description

<!-- description:start -->

<p>You are given a string array <code>words</code>, consisting of <strong>distinct</strong> 4-letter strings, each containing lowercase English letters.</p>

<p>A <strong>word square</strong> consists of 4 <strong>distinct</strong> words: <code>top</code>, <code>left</code>, <code>right</code> and <code>bottom</code>, arranged as follows:</p>

<ul>
	<li><code>top</code> forms the <strong>top row</strong>.</li>
	<li><code>bottom</code> forms the <strong>bottom row</strong>.</li>
	<li><code>left</code> forms the <strong>left column</strong> (top to bottom).</li>
	<li><code>right</code> forms the <strong>right column</strong> (top to bottom).</li>
</ul>

<p>It must satisfy:</p>

<ul>
	<li><code>top[0] == left[0]</code>, <code>top[3] == right[0]</code></li>
	<li><code>bottom[0] == left[3]</code>, <code>bottom[3] == right[3]</code></li>
</ul>

<p>Return all valid <strong>distinct</strong> word squares, sorted in <strong>ascending lexicographic</strong> order by the 4-tuple <code>(top, left, right, bottom)​​​​​​​</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;able&quot;,&quot;area&quot;,&quot;echo&quot;,&quot;also&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;able&quot;,&quot;area&quot;,&quot;echo&quot;,&quot;also&quot;],[&quot;area&quot;,&quot;able&quot;,&quot;also&quot;,&quot;echo&quot;]]</span></p>

<p><strong>Explanation:</strong></p>

<p>There are exactly two valid 4-word squares that satisfy all corner constraints:</p>

<ul>
	<li><code>&quot;able&quot;</code> (top), <code>&quot;area&quot;</code> (left), <code>&quot;echo&quot;</code> (right), <code>&quot;also&quot;</code> (bottom)

    <ul>
    	<li><code>top[0] == left[0] == &#39;a&#39;</code></li>
    	<li><code>top[3] == right[0] == &#39;e&#39;</code></li>
    	<li><code>bottom[0] == left[3] == &#39;a&#39;</code></li>
    	<li><code>bottom[3] == right[3] == &#39;o&#39;</code></li>
    </ul>
    </li>
    <li><code>&quot;area&quot;</code> (top), <code>&quot;able&quot;</code> (left), <code>&quot;also&quot;</code> (right), <code>&quot;echo&quot;</code> (bottom)
    <ul>
    	<li>All corner constraints are satisfied.</li>
    </ul>
    </li>

</ul>

<p>Thus, the answer is <code>[[&quot;able&quot;,&quot;area&quot;,&quot;echo&quot;,&quot;also&quot;],[&quot;area&quot;,&quot;able&quot;,&quot;also&quot;,&quot;echo&quot;]]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;code&quot;,&quot;cafe&quot;,&quot;eden&quot;,&quot;edge&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">[]</span></p>

<p><strong>Explanation:</strong></p>

<p>No combination of four words satisfies all four corner constraints. Thus, the answer is empty array <code>[]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>4 &lt;= words.length &lt;= 15</code></li>
	<li><code>words[i].length == 4</code></li>
	<li><code>words[i]</code> consists of only lowercase English letters.</li>
	<li>All <code>words[i]</code> are <strong>distinct</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def wordSquares(self, words: List[str]) -> List[List[str]]:
        words.sort()
        n = len(words)
        ans = []
        for i in range(n):
            top = words[i]
            for j in range(n):
                if j != i:
                    left = words[j]
                    for k in range(n):
                        if k != j and k != i:
                            right = words[k]
                            for h in range(n):
                                if h != k and h != j and h != i:
                                    bottom = words[h]
                                    if (
                                        top[0] == left[0]
                                        and top[3] == right[0]
                                        and bottom[0] == left[3]
                                        and bottom[3] == right[3]
                                    ):
                                        ans.append([top, left, right, bottom])
        return ans
```

#### Java

```java
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        Arrays.sort(words);
        int n = words.length;
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String top = words[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    String left = words[j];
                    for (int k = 0; k < n; k++) {
                        if (k != j && k != i) {
                            String right = words[k];
                            for (int h = 0; h < n; h++) {
                                if (h != k && h != j && h != i) {
                                    String bottom = words[h];
                                    if (top.charAt(0) == left.charAt(0)
                                        && top.charAt(3) == right.charAt(0)
                                        && bottom.charAt(0) == left.charAt(3)
                                        && bottom.charAt(3) == right.charAt(3)) {
                                        ans.add(List.of(top, left, right, bottom));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<vector<string>> wordSquares(vector<string>& words) {
        ranges::sort(words);
        int n = words.size();
        vector<vector<string>> ans;

        for (int i = 0; i < n; i++) {
            string top = words[i];
            for (int j = 0; j < n; j++) {
                if (j != i) {
                    string left = words[j];
                    for (int k = 0; k < n; k++) {
                        if (k != j && k != i) {
                            string right = words[k];
                            for (int h = 0; h < n; h++) {
                                if (h != k && h != j && h != i) {
                                    string bottom = words[h];
                                    if (top[0] == left[0] && top[3] == right[0] && bottom[0] == left[3] && bottom[3] == right[3]) {
                                        ans.push_back({top, left, right, bottom});
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func wordSquares(words []string) [][]string {
	sort.Strings(words)
	n := len(words)
	ans := [][]string{}

	for i := 0; i < n; i++ {
		top := words[i]
		for j := 0; j < n; j++ {
			if j != i {
				left := words[j]
				for k := 0; k < n; k++ {
					if k != j && k != i {
						right := words[k]
						for h := 0; h < n; h++ {
							if h != k && h != j && h != i {
								bottom := words[h]
								if top[0] == left[0] &&
									top[3] == right[0] &&
									bottom[0] == left[3] &&
									bottom[3] == right[3] {
									ans = append(ans, []string{top, left, right, bottom})
								}
							}
						}
					}
				}
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function wordSquares(words: string[]): string[][] {
    words.sort();
    const n = words.length;
    const ans: string[][] = [];

    for (let i = 0; i < n; i++) {
        const top = words[i];
        for (let j = 0; j < n; j++) {
            if (j !== i) {
                const left = words[j];
                for (let k = 0; k < n; k++) {
                    if (k !== j && k !== i) {
                        const right = words[k];
                        for (let h = 0; h < n; h++) {
                            if (h !== k && h !== j && h !== i) {
                                const bottom = words[h];
                                if (
                                    top[0] === left[0] &&
                                    top[3] === right[0] &&
                                    bottom[0] === left[3] &&
                                    bottom[3] === right[3]
                                ) {
                                    ans.push([top, left, right, bottom]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
